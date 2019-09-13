package com.altigee.needoff.auth.exception;

public class InvalidTokenException extends SecurityException {
  public InvalidTokenException(String message) {
    super(message);
  }
}
