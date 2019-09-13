package com.altigee.needoff.auth.exception;

public class ExpiredTokenException extends SecurityException {
  public ExpiredTokenException(String message) {
    super(message);
  }
}
