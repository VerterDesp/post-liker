package com.postliker.exception;

public class EditAlienPostException extends RuntimeException {
  private final String message;
  public EditAlienPostException() {
    this.message = "You can edit only own posts!";
  }

  public EditAlienPostException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
