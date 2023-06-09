package com.postliker.service.impl;

import com.postliker.auth.AuthResponse;
import com.postliker.auth.LoginRequest;
import com.postliker.auth.RegisterRequest;
import com.postliker.config.jwt.JwtService;
import com.postliker.exception.EmailAlreadyRegisteredException;
import com.postliker.model.Token;
import com.postliker.model.User;
import com.postliker.model.enums.Role;
import com.postliker.model.enums.TokenType;
import com.postliker.repository.TokenRepository;
import com.postliker.repository.UserRepository;
import com.postliker.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthServiceImpl(UserRepository userRepository,
                         TokenRepository tokenRepository,
                         PasswordEncoder passwordEncoder,
                         JwtService jwtService,
                         AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.tokenRepository = tokenRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

  public void register(RegisterRequest request) {
    if(userRepository.findByEmail(request.getEmail()).isEmpty()) {
      User user = new User(request.getName(),
                           request.getEmail(),
                           passwordEncoder.encode(request.getPassword()),
                           Role.USER);

      userRepository.save(user);
    } else {
      throw new EmailAlreadyRegisteredException();
    }
  }

  public AuthResponse login(LoginRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    User user = userRepository.findByEmail(request.getEmail())
                              .orElseThrow();

    String jwtToken = jwtService.generateToken(user.getUsername());

    revokeAllUserTokens(user);
    saveUserToken(user.getId(), jwtToken);
    return new AuthResponse(jwtToken);
  }

  private void saveUserToken(String userId, String jwtToken) {
    tokenRepository.save(new Token(jwtToken, TokenType.BEARER, false, false, userId));
  }

  private void revokeAllUserTokens(User user) {
    List<Token> validUserTokens = tokenRepository.findByExpiredIsFalseAndRevokedIsFalseAndUserId(user.getId());
    if (validUserTokens.isEmpty()) {
      return;
    }

    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }
}
