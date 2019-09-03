package com.altigee.needoff.graphqlapi.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class GqlOrganization {

    private Long id;
    private String name;
    private OffsetDateTime createdTime;
}
