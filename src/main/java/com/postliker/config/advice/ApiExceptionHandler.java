package com.postliker.config.advice;

import com.postliker.exception.DeleteAlienPostException;
import com.postliker.exception.EditAlienPostException;
import com.postliker.exception.EmailAlreadyRegisteredException;
import com.postliker.exception.PostDoesntExistException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(EmailAlreadyRegisteredException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String handleEmailAlreadyRegisteredException( EmailAlreadyRegisteredException exception ) {
    return exception.getMessage();
  }

  @ExceptionHandler(EditAlienPostException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public String handleEditAlienPostException( EditAlienPostException exception ) {
    return exception.getMessage();
  }

  @ExceptionHandler(DeleteAlienPostException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public String handleDeleteAlienPostException( DeleteAlienPostException exception ) {
    return exception.getMessage();
  }

  @ExceptionHandler(PostDoesntExistException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handlePostDoesntExistException( PostDoesntExistException exception ) {
    return exception.getMessage();
  }

  @ExceptionHandler(BadCredentialsException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public String handleBadCredentialsException() {
    return "No user with given credentials!";
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  public ApiError handleMethodArgumentNotValidException( MethodArgumentNotValidException exception ) {
    return new ApiError(exception
      .getBindingResult()
      .getAllErrors()
      .stream()
      .map(DefaultMessageSourceResolvable::getDefaultMessage)
      .toList());
  }
}
