package com.altigee.needoff.auth.service;

import com.altigee.needoff.auth.data.AccountRepo;
import com.altigee.needoff.auth.data.RoleRepo;
import com.altigee.needoff.auth.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

  @Autowired private AccountRepo accountRepo;

  @Autowired private RoleRepo roleRepo;

  @Override
  public Optional<Account> grantAccountAdminRoles(Long accountId) {

    return accountRepo
        .findById(accountId)
        .map(
            account -> {
              account.setRoles(roleRepo.findAll());
              return account;
            })
        .map(accountRepo::save);
  }
}
