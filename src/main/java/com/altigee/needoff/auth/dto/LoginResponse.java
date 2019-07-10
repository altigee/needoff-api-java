package com.altigee.needoff.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class LoginResponse {
  private String accessToken;
  private String refreshToken;
}