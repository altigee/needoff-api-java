package com.altigee.needoff.users.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity @Data
public class UserProfile {
  @Id
  private Long id;
  private Long accountId;

  private String firstName;
  private String lastName;
  private String position;
  private String phone;
  private String email;
}
