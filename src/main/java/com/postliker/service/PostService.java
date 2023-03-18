package com.postliker.service;

import com.postliker.dto.PostDto;

import java.util.List;

public interface PostService {
  List<PostDto> getAllPosts(String sortType);

  void savePost(String note);

  void deletePost(String postId);

  void editPost(String note, String postId);
}
