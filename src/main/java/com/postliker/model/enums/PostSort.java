package com.postliker.model.enums;

public enum PostSort {
  NEWEST("newest"), OLDEST("oldest");

  private final String type;

  PostSort(String type) {
    this.type = type;
  }

  public String type() {
    return type;
  }
}
