package com.postliker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WrongCredentialsException extends RuntimeException{
  private final String message;
  public WrongCredentialsException() {
    this.message = "No user with given credentials!";
  }

  public WrongCredentialsException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
