package com.altigee.needoff.graphqlapi.mutation;

import com.altigee.needoff.auth.service.AccountService;
import com.altigee.needoff.auth.service.AuthenticationFacade;
import com.altigee.needoff.graphqlapi.error.GqlError;
import com.altigee.needoff.graphqlapi.mapper.GqlAccountMapper;
import com.altigee.needoff.graphqlapi.model.GqlAccount;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class AccountResolver implements GraphQLMutationResolver {

  @Autowired private AuthenticationFacade authenticationFacade;
  @Autowired private AccountService accountService;

  // alternative -> @Secured("ROLE_ADMIN")
  @PreAuthorize("hasRole('ADMIN')")
  public GqlAccount grantAccountAdminRoles(Long accountId) {

    return accountService
        .grantAccountAdminRoles(accountId)
        .map(GqlAccountMapper.INSTANCE::toGraphQL)
        .orElseThrow(() -> new GqlError("cannot associate account with a token"));
  }
}
