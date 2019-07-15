package com.altigee.needoff.shared.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class Ctx {
  private Ctx() {}

  public static Long currentUserId() {
    return Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
  }
}
