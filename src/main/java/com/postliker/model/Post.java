package com.postliker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document
public class Post {

  @Id
  private String id;

  private String note;

  private List<String> idsWhoLiked = new ArrayList<>();

  private String authorId;

  private String authorName;

  private LocalDateTime createdAt = LocalDateTime.now();

  public Post(String note) {
    this.note = note;
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

  public List<String> getIdsWhoLiked() {
    return idsWhoLiked;
  }

  public void setIdsWhoLiked(List<String> idsWhoLiked) {
    this.idsWhoLiked = idsWhoLiked;
  }

  public String getAuthorId() {
    return authorId;
  }

  public void setAuthorId(String authorId) {
    this.authorId = authorId;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Post post = (Post) o;
    return Objects.equals(id, post.id) && Objects.equals(note, post.note) && Objects.equals(idsWhoLiked, post.idsWhoLiked) && Objects.equals(authorId, post.authorId) && Objects.equals(authorName, post.authorName) && Objects.equals(createdAt, post.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, note, idsWhoLiked, authorId, authorName, createdAt);
  }

  @Override
  public String toString() {
    return "Post{" +
      "id='" + id + '\'' +
      ", note='" + note + '\'' +
      ", idsWhoLiked=" + idsWhoLiked +
      ", authorId='" + authorId + '\'' +
      ", authorName='" + authorName + '\'' +
      ", createdAt=" + createdAt +
      '}';
  }
}
