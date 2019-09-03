package com.altigee.needoff.organization.service;

import com.altigee.needoff.organization.model.Organization;

import java.util.Optional;

public interface OrganizationService {
    Organization save(Organization organization);

    Optional<Organization> find(Long id);
}
