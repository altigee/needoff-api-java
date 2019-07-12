package com.altigee.needoff.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

// this was called "User" in Python api
@Entity @Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Account {
  @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String password;
  private String salt;
  private LocalDateTime createdTime;
  private String email;
  private String refreshTokenJti;
}
