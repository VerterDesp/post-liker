package com.postliker.exception;

public class DeleteAlienPostException extends RuntimeException {
  private final String message;
  public DeleteAlienPostException() {
    this.message = "You can delete only own posts!";
  }

  public DeleteAlienPostException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
