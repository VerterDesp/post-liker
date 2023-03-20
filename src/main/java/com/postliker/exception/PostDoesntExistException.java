package com.postliker.exception;

public class PostDoesntExistException extends RuntimeException {
  private final String message;

  public PostDoesntExistException() {
    this.message = "No post with given id!";
  }

  public PostDoesntExistException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
