package com.altigee.needoff.users.model;

import com.altigee.needoff.auth.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id", unique = true)
  private Account account;

  private String firstName;
  private String lastName;
  private String position;
  private String phone;
  private String email;
}
