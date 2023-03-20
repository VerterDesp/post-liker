package com.postliker.auth;

import com.postliker.config.advice.ApiValidationMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class RegisterRequest {

  @NotEmpty(message = ApiValidationMessages.REGISTER_NAME_EMPTY)
  private String name;

  @NotEmpty(message = ApiValidationMessages.REGISTER_EMAIL_EMPTY)
  @Email(message = ApiValidationMessages.REGISTER_EMAIL_NOT_VALID)
  private String email;

  @NotEmpty(message = ApiValidationMessages.PASSWORD_PASSWORD_EMPTY)
  private String password;

  public RegisterRequest(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
    RegisterRequest that = (RegisterRequest) o;
    return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, email, password);
  }

  @Override
  public String toString() {
    return "RegisterRequest{" +
      "name='" + name + '\'' +
      ", email='" + email + '\'' +
      ", password='" + password + '\'' +
      '}';
  }
}
