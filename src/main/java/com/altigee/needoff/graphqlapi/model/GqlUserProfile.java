package com.altigee.needoff.graphqlapi.model;

import lombok.Data;

@Data
public class GqlUserProfile {
  private Long id;
  private Long userId; // todo maybe rename later, just trying out MapStruct

  private String firstName;
  private String lastName;
  private String position;
  private String phone;
  private String email;
}
