package com.altigee.needoff.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

// this was called "User" in Python api
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String password;
  private String salt;
  private LocalDateTime createdTime;
  private String email;
  private String refreshTokenJti;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
      name = "account_role",
      joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private List<Role> roles;
}
