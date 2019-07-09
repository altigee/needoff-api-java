package com.altigee.needoff.healthcheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingPong {
  @GetMapping("/ping")
  public String pong() {
    return "pong";
  }
}
