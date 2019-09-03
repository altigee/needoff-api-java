package com.altigee.needoff.graphqlapi.mapper;

import com.altigee.needoff.graphqlapi.model.GqlCreateOrganization;
import com.altigee.needoff.graphqlapi.model.GqlOrganization;
import com.altigee.needoff.organization.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper
public interface GqlOrganizationMapper {

    GqlOrganizationMapper INSTANCE = Mappers.getMapper(GqlOrganizationMapper.class);

    Organization graphQLToOrganization(GqlCreateOrganization organization);

    @Mapping(target = "createdTime", source = "createdTime")
    GqlOrganization organizationToGql(Organization organization);

    @SuppressWarnings(value = "unused")
    default OffsetDateTime toCreatedTimeFromCreatedTime(LocalDateTime time) {
        return OffsetDateTime.of(time, ZoneOffset.UTC);
    }
}
