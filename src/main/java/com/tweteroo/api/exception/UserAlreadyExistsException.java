package com.tweteroo.api.exception;

public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException() {
    super("An user with this username already exists!");
  }
}
