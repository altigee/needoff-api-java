package com.altigee.needoff.auth.service;

import com.altigee.needoff.auth.data.AccountRepo;
import com.altigee.needoff.auth.data.RoleRepo;
import com.altigee.needoff.auth.model.Account;
import com.altigee.needoff.auth.model.Role;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

  @Autowired private AccountRepo accountRepo;

  @Autowired private RoleRepo roleRepo;

  @Override
  public Optional<Account> grantAccount(Long accountId) {

    return accountRepo
        .findById(accountId)
        .map(account -> applyRolesToAccount(account, Sets.newHashSet(roleRepo.findAll())))
        .map(accountRepo::save);
  }

  @Override
  public Optional<Account> grantRoles(Long accountId, Set<Role.Type> roles) {

    return accountRepo
        .findById(accountId)
        .map(account -> this.applyRolesToAccount(account, getRoles(roles)))
        .map(accountRepo::save);
  }

  @Override
  public Optional<Account> find(Long accountId) {
    return accountRepo.findById(accountId);
  }

  @Override
  public Account update(Account account) {
    return accountRepo.save(account);
  }

  @Override
  public Optional<Account> setStatus(Long accountId, Account.Status status) {
    return this.find(accountId)
        .map(
            account -> {
              account.setStatus(status);
              return account;
            })
        .map(this::update);
  }

  private Account applyRolesToAccount(Account account, Set<Role> roles) {
    account.setRoles(roles);
    return account;
  }

  private Set<Role> getRoles(Set<Role.Type> roleTypes) {
    return roleRepo.findAll().stream()
        .filter(role -> roleTypes.contains(role.getName()))
        .collect(Collectors.toSet());
  }
}
