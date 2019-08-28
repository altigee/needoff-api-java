package com.altigee.needoff.auth.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data @Builder
public class ParsedToken {
  private String subject;
  private Date issuedAt;
  private Date expiresAt;
  private String jti;
  private List<? extends GrantedAuthority> authorities;
}
