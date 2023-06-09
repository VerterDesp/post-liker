package com.postliker.repository;

import com.postliker.model.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends MongoRepository<Token, String> {

//  @Query(value = """
//      select t from Token t inner join User u\s
//      on t.user.id = u.id\s
//      where u.id = :id and (t.expired = false or t.revoked = false)\s
//      """)
  List<Token> findByExpiredIsFalseAndRevokedIsFalseAndUserId(String userId);

  Optional<Token> findByToken(String token);
}
