package com.altigee.needoff.healthcheck;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PingPong {
  @GetMapping("/ping")
  public String pong() {
    return "Your ID is " + SecurityContextHolder.getContext().getAuthentication().getName();
  }
}
