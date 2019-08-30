package com.altigee.needoff.auth.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.Set;

@Data @Builder
public class ParsedToken {
  private String subject;
  private Date issuedAt;
  private Date expiresAt;
  private String jti;
  private Set<? extends GrantedAuthority> authorities;
}
