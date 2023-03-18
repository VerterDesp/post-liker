package com.postliker;

import com.postliker.model.Post;
import com.postliker.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PostLiker {

  public static void main(String[] args) {
    SpringApplication.run(PostLiker.class, args);
  }

  @Bean
  CommandLineRunner runner(PostRepository repository) {
    return args -> {
      String note = "my first note";
      Post post1 = new Post(note);
      repository.insert(post1);
    };
  }


}
