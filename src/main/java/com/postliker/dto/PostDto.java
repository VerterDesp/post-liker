package com.postliker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.postliker.model.User;

import java.time.LocalDateTime;
import java.util.Objects;

public class PostDto {

  private String note;

  private int likeCount;

  @JsonIgnore
  private User author;

//  @JsonIgnore
  private LocalDateTime createdAt;

  public PostDto(String note) {
    this.note = note;
  }

  public PostDto(String note, int likeCount) {
    this.note = note;
    this.likeCount = likeCount;
  }

  public PostDto(String note, int likeCount, LocalDateTime createdAt) {
    this.note = note;
    this.likeCount = likeCount;
    this.createdAt = createdAt;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public int getLikeCount() {
    return likeCount;
  }

  public void setLikeCount(int likeCount) {
    this.likeCount = likeCount;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PostDto postDto = (PostDto) o;
    return likeCount == postDto.likeCount && Objects.equals(note, postDto.note) && Objects.equals(author, postDto.author) && Objects.equals(createdAt, postDto.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(note, likeCount, author, createdAt);
  }

  @Override
  public String toString() {
    return "PostDto{" +
      "note='" + note + '\'' +
      ", likeCount=" + likeCount +
      ", author=" + author +
      ", createdAt=" + createdAt +
      '}';
  }
}
