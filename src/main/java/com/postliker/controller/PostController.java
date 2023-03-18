package com.postliker.controller;

import com.postliker.dto.PostDto;
import com.postliker.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("all")
  public List<PostDto> getAllPosts(@RequestParam(required = false) String sortType) {
    return postService.getAllPosts(sortType);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("create")
  public void createNewPost(@RequestBody String note) {
    postService.savePost(note);
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @DeleteMapping("delete/{postId}")
  public void deletePost(@PathVariable String postId) {
    postService.deletePost(postId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("edit/{postId}")
  public void editPost(@RequestBody String note, @PathVariable String postId) {
    postService.editPost(note, postId);
  }

  @PutMapping("like/{postId}")
  public void addLike(@PathVariable String postId) {

  }
}
