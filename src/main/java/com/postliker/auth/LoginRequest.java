package com.postliker.auth;

import com.postliker.config.advice.ApiValidationMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class LoginRequest {

  @NotEmpty(message = ApiValidationMessages.LOGIN_EMAIL_EMPTY)
  @Email(message = ApiValidationMessages.LOGIN_EMAIL_NOT_VALID)
  private String email;

  @NotEmpty(message = ApiValidationMessages.LOGIN_PASSWORD_EMPTY)
  private String password;

  public LoginRequest(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LoginRequest that = (LoginRequest) o;
    return Objects.equals(email, that.email) && Objects.equals(password, that.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password);
  }

  @Override
  public String toString() {
    return "AuthenticationRequest{" +
      "email='" + email + '\'' +
      ", password='" + password + '\'' +
      '}';
  }
}
