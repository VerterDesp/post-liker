package com.postliker.service.impl;

import com.postliker.dto.PostDto;
import com.postliker.model.enums.PostSortType;
import com.postliker.service.PostService;
import com.postliker.model.Post;
import com.postliker.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;

  public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public List<PostDto> getAllPosts(String sortType) {
    return postRepository
      .findAll()
      .stream()
      .sorted((post1, post2) -> {
        if (Objects.equals(sortType, PostSortType.OLDEST.type())) {
          return post1.getCreatedAt().compareTo(post2.getCreatedAt());
        } else if (Objects.equals(sortType, PostSortType.NEWEST.type())) {
          return post2.getCreatedAt().compareTo(post1.getCreatedAt());
        } return 0;
      })
      .map(post -> new PostDto(
        post.getNote(),
        post.getLikeCount(),
        post.getCreatedAt()))
      .toList();
  }

  public List<PostDto> getAllPosts() {
    return postRepository.findAll()
      .stream()
      .map(post -> new PostDto(post.getNote(), post.getLikeCount(), post.getCreatedAt()))
      .toList();
  }

  public void savePost(String note) {
    boolean isRegistered = false;
    Post post = new Post(note);
    if(isRegistered) {
      //post.setAuthor(new User("n", "e", "p"));
    }
    postRepository.save(post);
  }

  public void deletePost(String postId) {
    postRepository.deleteById(postId);
  }

  public void editPost(String note, String postId) {
    postRepository.findById(postId).ifPresentOrElse(post -> {
      post.setNote(note);
      postRepository.save(post);
    }, () -> { throw new RuntimeException("No post with given id - " + postId); });
  }

  public void addLike() {

  }
}
