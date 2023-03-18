package com.postliker.model.enums;

public enum PostSortType {
  NEWEST("newest"), OLDEST("oldest");

  private final String type;

  PostSortType(String type) {
    this.type = type;
  }

  public String type() {
    return type;
  }
}
