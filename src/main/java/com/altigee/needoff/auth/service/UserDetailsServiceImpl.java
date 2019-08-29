package com.altigee.needoff.auth.service;

import com.altigee.needoff.auth.data.AccountRepo;
import com.altigee.needoff.auth.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired private AccountRepo accountRepo;

  @Override
  public UserDetails loadUserByUsername(String username) {

    return accountRepo
        .findById(Long.parseLong(username))
        .map(UserPrincipal::new)
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }
}
