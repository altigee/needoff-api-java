package com.altigee.needoff.auth.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data @Builder
public class ParsedToken {
  private String subject;
  private Date issuedAt;
  private Date expiresAt;
  private String jti;
}
