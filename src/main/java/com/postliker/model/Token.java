package com.postliker.model;

import com.postliker.model.enums.TokenType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Token {

  @Id
  private String id;

  @Indexed(unique = true)
  private String token;

  private TokenType tokenType;

  private boolean revoked;

  private boolean expired;

  private String userId;

  public Token(String token, TokenType tokenType, boolean revoked, boolean expired, String userId) {
    this.token = token;
    this.tokenType = tokenType;
    this.revoked = revoked;
    this.expired = expired;
    this.userId = userId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public TokenType getTokenType() {
    return tokenType;
  }

  public void setTokenType(TokenType tokenType) {
    this.tokenType = tokenType;
  }

  public boolean isRevoked() {
    return revoked;
  }

  public void setRevoked(boolean revoked) {
    this.revoked = revoked;
  }

  public boolean isExpired() {
    return expired;
  }

  public void setExpired(boolean expired) {
    this.expired = expired;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Token token1 = (Token) o;
    return revoked == token1.revoked && expired == token1.expired && Objects.equals(id, token1.id) && Objects.equals(token, token1.token) && tokenType == token1.tokenType && Objects.equals(userId, token1.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, token, tokenType, revoked, expired, userId);
  }

  @Override
  public String toString() {
    return "Token{" +
      "id='" + id + '\'' +
      ", token='" + token + '\'' +
      ", tokenType=" + tokenType +
      ", revoked=" + revoked +
      ", expired=" + expired +
      ", userId='" + userId + '\'' +
      '}';
  }
}
