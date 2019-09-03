package com.altigee.needoff.organization.service;

import com.altigee.needoff.organization.data.OrganizationRepo;
import com.altigee.needoff.organization.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepo organizationRepo;

    @Override public Organization save(Organization organization) {
        return organizationRepo.save(organization);
    }

    @Override public Optional<Organization> find(Long id) {
        return organizationRepo.findById(id);
    }
}
