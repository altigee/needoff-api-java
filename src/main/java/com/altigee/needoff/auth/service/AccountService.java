package com.altigee.needoff.auth.service;

import com.altigee.needoff.auth.model.Account;

import java.util.Optional;

public interface AccountService {

    Optional<Account> grantAccountAdminRoles(Long accountId);


}
