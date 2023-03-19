package com.postliker.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class PostDto {

  private String id;

  private String note;

  private int likeCount;

  private String author;

  private LocalDateTime createdAt;

  public PostDto(String id, String note, int likeCount, String author, LocalDateTime createdAt) {
    this.id = id;
    this.note = note;
    this.likeCount = likeCount;
    this.author = author;
    this.createdAt = createdAt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
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
    return likeCount == postDto.likeCount && Objects.equals(id, postDto.id) && Objects.equals(note, postDto.note) && Objects.equals(author, postDto.author) && Objects.equals(createdAt, postDto.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, note, likeCount, author, createdAt);
  }

  @Override
  public String toString() {
    return "PostDto{" +
      "id='" + id + '\'' +
      ", note='" + note + '\'' +
      ", likeCount=" + likeCount +
      ", author='" + author + '\'' +
      ", createdAt=" + createdAt +
      '}';
  }
}
