package com.altigee.needoff.auth.service;

import com.altigee.needoff.auth.data.AccountRepo;
import com.altigee.needoff.auth.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

  @Autowired private AccountRepo accountRepo;

  @Override
  public Optional<Account> getAuthenticatedAccount() {
    return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
        .map(Authentication::getPrincipal)
        .filter(principal -> principal instanceof Long)
        .map(Long.class::cast)
        .flatMap(accountRepo::findById);
  }
}
