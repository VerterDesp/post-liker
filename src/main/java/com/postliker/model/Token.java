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

  private TokenType tokenType = TokenType.BEARER;

  private boolean revoked;

  private boolean expired;

//  @ManyToOne
//  @JoinColumn(name = "user_id")
  private User user;

  public Token() {}

  public Token(String token, TokenType tokenType, boolean revoked, boolean expired, User user) {
    this.token = token;
    this.tokenType = tokenType;
    this.revoked = revoked;
    this.expired = expired;
    this.user = user;
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Token token1 = (Token) o;
    return revoked == token1.revoked && expired == token1.expired && Objects.equals(id, token1.id) && Objects.equals(token, token1.token) && tokenType == token1.tokenType && Objects.equals(user, token1.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, token, tokenType, revoked, expired, user);
  }

  @Override
  public String toString() {
    return "Token{" +
      "id=" + id +
      ", token='" + token + '\'' +
      ", tokenType=" + tokenType +
      ", revoked=" + revoked +
      ", expired=" + expired +
      ", user=" + user +
      '}';
  }
}
