package com.altigee.needoff.auth.http;

import com.altigee.needoff.auth.dto.*;
import com.altigee.needoff.auth.exception.*;
import com.altigee.needoff.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth/v1")
public class AuthV1Controller {
  private final AuthService authService;

  @Autowired
  public AuthV1Controller(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/register")
  public RegisterResponse register(@Valid @RequestBody RegisterRequest request) throws AccountExistsException {
    return authService.register(request);
  }

  @PostMapping("/login")
  public LoginResponse login(@Valid @RequestBody LoginRequest request) throws AccountDoesNotExistException, WrongCredentialsException {
    return authService.login(request);
  }

  @PostMapping("/refresh")
  public RefreshTokenResponse refresh(@Valid @RequestBody RefreshTokenRequest request) throws ExpiredTokenException, InvalidTokenException, AccountDoesNotExistException {
    return authService.refreshToken(request);
  }
}
