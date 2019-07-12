package com.altigee.needoff.graphqlapi.model;

import lombok.Data;

@Data
public class GqlUpdateProfile {
  private String firstName;
  private String lastName;
  private String position;
  private String phone;
  private String email;
}
