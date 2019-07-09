package com.altigee.needoff.auth.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

// this was called "User" in Python api
@Entity @Data
public class Account {
  @Id
  private Long id;
  private String password;
  private LocalDateTime createdTime;
  private String email;
}
