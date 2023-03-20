package com.postliker.config.advice;

public class ApiValidationMessages {
  private ApiValidationMessages() {}

  /**
   * LoginRequest - validation error messages.
   * @see com.postliker.auth.LoginRequest
   */
  public static final String LOGIN_EMAIL_EMPTY = "Email field can't be blank!";
  public static final String LOGIN_EMAIL_NOT_VALID = "Enter valid email!";
  public static final String LOGIN_PASSWORD_EMPTY = "Password field can't be blank!";

  /**
   * RegisterRequest - validation error messages.
   * @see com.postliker.auth.RegisterRequest
   */
  public static final String REGISTER_NAME_EMPTY = "Name field can't be blank!";
  public static final String REGISTER_EMAIL_EMPTY = "Email field can't be blank!";
  public static final String REGISTER_EMAIL_NOT_VALID = "Enter valid email!";
  public static final String PASSWORD_PASSWORD_EMPTY = "Password field can't be blank!";
}
