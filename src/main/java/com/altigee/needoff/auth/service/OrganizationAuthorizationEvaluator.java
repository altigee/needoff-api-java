package com.altigee.needoff.auth.service;

import com.altigee.needoff.auth.model.Account;
import com.altigee.needoff.organization.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("authEval")
public class OrganizationAuthorizationEvaluator implements AuthorizationEvaluator {

    @Autowired private AuthenticationFacade authenticationFacade;

    @Override
    public boolean hasPermission(Long resourceId) {
        return authenticationFacade
            .getAuthenticatedAccount()
            .map(Account::getOrganization)
            .map(Organization::getId)
            .filter(orgId -> orgId.equals(resourceId))
            .isPresent();
    }
}
