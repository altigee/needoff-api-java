package com.altigee.needoff.auth.exception;

public class ExpiredTokenException extends Exception {
  public ExpiredTokenException(String message) {
    super(message);
  }
}
