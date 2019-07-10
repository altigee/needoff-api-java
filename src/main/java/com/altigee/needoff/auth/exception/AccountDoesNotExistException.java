package com.altigee.needoff.auth.exception;

public class AccountDoesNotExistException extends Exception {
  public AccountDoesNotExistException() {
    super("Account not found");
  }
}