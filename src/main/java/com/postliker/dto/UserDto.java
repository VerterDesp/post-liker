package com.postliker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class UserDto {

  @NotEmpty
  private String name;

  @Email
  @NotEmpty
  private String email;

  @NotEmpty
  private String password;

  public UserDto(String name, String email, String password) {
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
    UserDto userDto = (UserDto) o;
    return Objects.equals(name, userDto.name) && Objects.equals(email, userDto.email) && Objects.equals(password, userDto.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, email, password);
  }

  @Override
  public String toString() {
    return "UserDto{" +
      "name='" + name + '\'' +
      ", email='" + email + '\'' +
      ", password='" + password + '\'' +
      '}';
  }
}
