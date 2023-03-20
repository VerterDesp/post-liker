package com.postliker.config;

import com.postliker.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(EmailAlreadyRegisteredException.class)
  public ResponseEntity<String> handleEmailAlreadyRegisteredException( EmailAlreadyRegisteredException exception ) {
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(exception.getMessage());
  }

  @ExceptionHandler(EditAlienPostException.class)
  public ResponseEntity<String> handleEditAlienPostException( EditAlienPostException exception ) {
    return ResponseEntity
      .status(HttpStatus.FORBIDDEN)
      .body(exception.getMessage());
  }

  @ExceptionHandler(DeleteAlienPostException.class)
  public ResponseEntity<String> handleDeleteAlienPostException( DeleteAlienPostException exception ) {
    return ResponseEntity
      .status(HttpStatus.FORBIDDEN)
      .body(exception.getMessage());
  }

  @ExceptionHandler(PostDoesntExistException.class)
  public ResponseEntity<String> handlePostDoesntExistException( PostDoesntExistException exception ) {
    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body(exception.getMessage());
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<String> handleBadCredentialsException() {
    return ResponseEntity
      .status(HttpStatus.FORBIDDEN)
      .body("No user with given credentials!");
  }
}
