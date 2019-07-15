package com.altigee.needoff.users.dto;

import lombok.Data;

@Data
public class UpdateProfileRequest {
  private String firstName;
  private String lastName;
  private String position;
  private String phone;
  private String email;
}
