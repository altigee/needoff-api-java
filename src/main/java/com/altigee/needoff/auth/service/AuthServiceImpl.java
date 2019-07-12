package com.altigee.needoff.auth.service;

import com.altigee.needoff.auth.data.AccountRepo;
import com.altigee.needoff.auth.dto.*;
import com.altigee.needoff.auth.exception.*;
import com.altigee.needoff.auth.model.Account;
import com.altigee.needoff.users.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
  public static final Logger LOG = LoggerFactory.getLogger(AuthServiceImpl.class);

  private final AccountRepo accountRepo;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final SaltCreator saltCreator;
  private final ProfileService profileService;

  @Autowired
  public AuthServiceImpl(AccountRepo accountRepo, JwtService jwtService, PasswordEncoder passwordEncoder, SaltCreator saltCreator, ProfileService profileService) {
    this.accountRepo = accountRepo;
    this.jwtService = jwtService;
    this.passwordEncoder = passwordEncoder;
    this.saltCreator = saltCreator;
    this.profileService = profileService;
  }

  @Override
  @Transactional
  public RegisterResponse register(RegisterRequest request) throws AccountExistsException {
    if (accountRepo.existsByEmail(request.getEmail())) {
      throw new AccountExistsException("Account with such email already exists");
    }

    var salt = saltCreator.randomSalt();
    var password = saltCreator.saltPassword(request.getPassword(), salt);
    var encodedPassword = passwordEncoder.encode(password);

    var account = Account.builder().email(request.getEmail())
        .createdTime(LocalDateTime.now())
        .password(encodedPassword)
        .salt(salt)
        .build();
    accountRepo.save(account);

    var jti = randomJti();
    var refreshToken = jwtService.newRefreshToken(account, jti);
    account.setRefreshTokenJti(jti);
    accountRepo.save(account);
    profileService.initProfile(account);

    var accessToken = jwtService.newAccessToken(account);

    return RegisterResponse.builder()
        .id(account.getId())
        .accessToken(accessToken)
        .refreshToken(refreshToken).build();
  }

  @Override
  public LoginResponse login(LoginRequest request) throws AccountDoesNotExistException, WrongCredentialsException {
    var account = accountRepo
        .findByEmail(request.getEmail())
        .orElseThrow(AccountDoesNotExistException::new);

    var salt = account.getSalt();
    var password = saltCreator.saltPassword(request.getPassword(), salt);

    if (!passwordEncoder.matches(password, account.getPassword())) {
      throw new WrongCredentialsException();
    }
    var accessToken = jwtService.newAccessToken(account);
    var jti = randomJti();
    var refreshToken = jwtService.newRefreshToken(account, jti);

    account.setRefreshTokenJti(jti);
    accountRepo.save(account);
    return LoginResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
  }

  @Override
  public RefreshTokenResponse refreshToken(RefreshTokenRequest request) throws AccountDoesNotExistException, ExpiredTokenException, InvalidTokenException {
    var jwt = jwtService.parseToken(request.getRefreshToken());
    var userId = Long.parseLong(jwt.getSubject());
    var account = accountRepo.findById(userId).orElseThrow(AccountDoesNotExistException::new);
    if (!account.getRefreshTokenJti().equals(jwt.getJti())) {
      throw new InvalidTokenException("JTI validation failed");
    }

    var accessToken = jwtService.newAccessToken(account);
    var jti = randomJti();
    var refreshToken = jwtService.newRefreshToken(account, jti);

    account.setRefreshTokenJti(jti);
    accountRepo.save(account);

    return RefreshTokenResponse.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }

  private static String randomJti() {
    return UUID.randomUUID().toString();
  }
}
