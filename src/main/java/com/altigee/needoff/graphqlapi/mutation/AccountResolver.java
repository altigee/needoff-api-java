package com.altigee.needoff.graphqlapi.mutation;

import com.altigee.needoff.auth.model.Account;
import com.altigee.needoff.auth.model.Role;
import com.altigee.needoff.auth.service.AccountService;
import com.altigee.needoff.auth.service.AuthenticationFacade;
import com.altigee.needoff.graphqlapi.error.GqlError;
import com.altigee.needoff.graphqlapi.mapper.GqlAccountMapper;
import com.altigee.needoff.graphqlapi.model.GqlAccount;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@SuppressWarnings("unused")
public class AccountResolver implements GraphQLMutationResolver {

  @Autowired private AuthenticationFacade authenticationFacade;
  @Autowired private AccountService accountService;

  // alternative -> @Secured("ROLE_ADMIN")
  @PreAuthorize("hasRole('ADMIN')")
  public GqlAccount grantAccountAdminRoles(Long accountId) {

    return accountService
        .grantAccountAllRoles(accountId)
        .map(GqlAccountMapper.INSTANCE::toGraphQL)
        .orElseThrow(() -> new GqlError("cannot associate roles with the account"));
  }

  @PreAuthorize("hasRole('ADMIN')")
  public GqlAccount grantRoles(Long accountId, Set<Role.Type> roles) {
    return accountService
        .grantRoles(accountId, roles)
        .map(GqlAccountMapper.INSTANCE::toGraphQL)
        .orElseThrow(() -> new GqlError("cannot associate roles with the account"));
  }

  @PreAuthorize("hasAnyRole('ADMIN', 'OWNER')")
  public GqlAccount setAccountStatus(Long accountId, String status) {
    return accountService
        .setStatus(accountId, Account.Status.valueOf(status))
        .map(GqlAccountMapper.INSTANCE::toGraphQL)
        .orElseThrow(() -> new GqlError("cannot change account status"));
  }

}
