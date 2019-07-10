package com.altigee.needoff.auth.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

@Component
public class SaltCreator {
  private final Random random = new SecureRandom();

  public String randomSalt() {
    byte[] salt = new byte[32];
    random.nextBytes(salt);
    return new String(Base64.encodeBase64(salt), StandardCharsets.US_ASCII);
  }

  public String saltPassword(String password, String salt) {
    return String.format("%s%s", password, salt);
  }
}