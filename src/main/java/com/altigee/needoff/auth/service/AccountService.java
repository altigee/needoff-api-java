package com.altigee.needoff.auth.service;

import com.altigee.needoff.auth.model.Account;
import com.altigee.needoff.auth.model.Role;

import java.util.Optional;
import java.util.Set;

public interface AccountService {

    Optional<Account> grantAccountAllRoles(Long accountId);

    Optional<Account> grantRoles(Long accountId, Set<Role.Type> roles);

    Optional<Account> find(Long accountId);

    Account update(Account account);

    Optional<Account> setStatus(Long accountId, Account.Status status);

}
