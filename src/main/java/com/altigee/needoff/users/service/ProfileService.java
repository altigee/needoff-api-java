package com.altigee.needoff.users.service;

import com.altigee.needoff.auth.model.Account;
import com.altigee.needoff.users.dto.UpdateProfileRequest;
import com.altigee.needoff.users.exception.UserProfileNotFoundException;
import com.altigee.needoff.users.model.UserProfile;

import java.util.Optional;

public interface ProfileService {
  void initProfile(Account account);
  Optional<UserProfile> getProfile(Long accountId);
  UserProfile updateProfile(Long accountId, UpdateProfileRequest request) throws UserProfileNotFoundException;
}
