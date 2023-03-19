package com.postliker.service.impl;

import com.postliker.dto.PostDto;
import com.postliker.exception.DeleteAlienPostException;
import com.postliker.exception.EditAlienPostException;
import com.postliker.exception.PostDoesntExistException;
import com.postliker.model.Post;
import com.postliker.model.enums.PostSort;
import com.postliker.repository.PostRepository;
import com.postliker.service.PostService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.postliker.config.UserUtils.*;

@Service
@Transactional
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;

  public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public List<PostDto> getAllPosts(String sortType) {
    return postRepository
      .findAll(Sort.by(PostSort.OLDEST.type().equals(sortType) ? Sort.Direction.ASC : Sort.Direction.DESC, "createdAt"))
      .stream()
      .map(post -> new PostDto(
        post.getId(),
        post.getNote(),
        post.getIdsWhoLiked().size(),
        post.getAuthorName()))
      .toList();
  }

  public void savePost(String note) {
    Post post = new Post(note);

    if(!isAnonymous()) {
      post.setAuthorId(getLoggedUserId());
      post.setAuthorName(getLoggedUserName());
    }
    postRepository.save(post);
  }

  public void deletePost(String postId) {
    postRepository.findById(postId).ifPresentOrElse(post -> {
      if (getLoggedUserId().equals(post.getAuthorId())) {
        postRepository.deleteById(postId);
      } else {
        throw new DeleteAlienPostException();
      }

    }, () -> { throw new PostDoesntExistException(); });
  }

  public void editPost(String note, String postId) {
    postRepository.findById(postId).ifPresentOrElse(post -> {

      if (getLoggedUserId().equals(post.getAuthorId())) {
        post.setNote(note);
        postRepository.save(post);
      } else {
        throw new EditAlienPostException();
      }

    }, () -> { throw new PostDoesntExistException(); });
  }

  public void like(String postId) {
    postRepository.findById(postId).ifPresentOrElse(post -> {

        List<String> idsWhoLiked = post.getIdsWhoLiked();
        String currentUserId = getLoggedUserId();
        boolean currentUserAlreadyLiked = idsWhoLiked
          .stream()
          .anyMatch(id -> id.equals(currentUserId));

        if (currentUserAlreadyLiked) {
          idsWhoLiked.remove(currentUserId);
        } else {
          idsWhoLiked.add(currentUserId);
        }

        post.setIdsWhoLiked(idsWhoLiked);
        postRepository.save(post);

      }, () -> { throw new PostDoesntExistException(); });
  }
}
