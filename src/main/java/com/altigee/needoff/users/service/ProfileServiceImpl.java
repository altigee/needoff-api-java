package com.altigee.needoff.users.service;

import com.altigee.needoff.auth.model.Account;
import com.altigee.needoff.users.data.UserProfileRepo;
import com.altigee.needoff.users.dto.UpdateProfileRequest;
import com.altigee.needoff.users.exception.UserProfileNotFoundException;
import com.altigee.needoff.users.model.UserProfile;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
  private UserProfileRepo repo;

  @Autowired
  public ProfileServiceImpl(UserProfileRepo repo) {
    this.repo = repo;
  }

  @Override
  public void initProfile(Account account) {
    repo.save(UserProfile.builder()
        .account(account)
        .email(account.getEmail())
        .build());
  }

  @Override
  public Optional<UserProfile> getProfile(Long accountId) {
    return repo.findByAccountId(accountId);
  }

  @Override
  public UserProfile updateProfile(Long accountId, UpdateProfileRequest request) throws UserProfileNotFoundException {
    var profile = getProfile(accountId).orElseThrow(UserProfileNotFoundException::new);
    if (StringUtils.isNotBlank(request.getEmail())) {
      profile.setEmail(request.getEmail());
      // todo update account ???
    }
    if (StringUtils.isNotBlank(request.getFirstName())) {
      profile.setFirstName(request.getFirstName());
    }
    if (StringUtils.isNotBlank(request.getLastName())) {
      profile.setLastName(request.getLastName());
    }
    if (StringUtils.isNotBlank(request.getPhone())) {
      profile.setPhone(request.getPhone());
    }
    if (StringUtils.isNotBlank(request.getPosition())) {
      profile.setPosition(request.getPosition());
    }
    return repo.save(profile);
  }
}
