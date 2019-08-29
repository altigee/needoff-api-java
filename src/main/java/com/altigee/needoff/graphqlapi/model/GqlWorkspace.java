package com.altigee.needoff.graphqlapi.model;

import lombok.Data;

@Data
public class GqlWorkspace {

  private Long id;
  private String name;
  private String description;
  private String invitationLinkToken;
}
