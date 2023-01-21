package com.tweteroo.api.exception;

public class UserNotExists extends RuntimeException {
  public UserNotExists() {
    super("User not found!");
  }
}
