package com.altigee.needoff.graphqlapi.mutation;

import com.altigee.needoff.auth.service.AccountService;
import com.altigee.needoff.auth.service.AuthenticationFacade;
import com.altigee.needoff.graphqlapi.error.GqlError;
import com.altigee.needoff.graphqlapi.mapper.GqlOrganizationMapper;
import com.altigee.needoff.graphqlapi.model.GqlCreateOrganization;
import com.altigee.needoff.graphqlapi.model.GqlOrganization;
import com.altigee.needoff.graphqlapi.model.GqlUpdateOrganization;
import com.altigee.needoff.organization.service.OrganizationService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import static com.altigee.needoff.shared.util.OptionalUtil.peek;

@Component
public class OrganizationResolver implements GraphQLMutationResolver {

  @Autowired private AuthenticationFacade authenticationFacade;
  @Autowired private OrganizationService organizationService;
  @Autowired private AccountService accountService;

  public GqlOrganization saveOrganization(GqlCreateOrganization organization) {

    var org =
        organizationService.save(
            GqlOrganizationMapper.INSTANCE.graphQLToOrganization(organization));

    authenticationFacade
        .getAuthenticatedAccount()
        .map(peek(creator -> creator.setOrganization(org)))
        .map(peek(accountService::update))
        .orElseThrow(() -> new GqlError("organization can't be associated with the account"));

    return GqlOrganizationMapper.INSTANCE.organizationToGql(org);
  }

  @PreAuthorize("@authEval.hasPermission(#organization.id) and hasAnyRole('ADMIN', 'OWNER')")
  public GqlOrganization updateOrganization(GqlUpdateOrganization organization) {

    return organizationService
        .find(organization.getId())
        .map(peek(org -> org.setName(organization.getName())))
        .map(organizationService::save)
        .map(GqlOrganizationMapper.INSTANCE::organizationToGql)
        .orElseThrow(() -> new GqlError("organization can't be updated"));
  }
}
