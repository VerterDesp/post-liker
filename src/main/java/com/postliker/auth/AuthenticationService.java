package com.postliker.auth;

import com.postliker.config.JwtService;
import com.postliker.model.Token;
import com.postliker.model.User;
import com.postliker.model.enums.Role;
import com.postliker.model.enums.TokenType;
import com.postliker.repository.TokenRepository;
import com.postliker.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthenticationService {

  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationService(UserRepository userRepository,
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

  public AuthenticationResponse register(RegisterRequest request) {
    User user = new User(request.getName(), request.getEmail(),
      passwordEncoder.encode(request.getPassword()), Role.USER);

    var savedUser = userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
    return new AuthenticationResponse(jwtToken);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = userRepository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return new AuthenticationResponse(jwtToken);
  }

  private void saveUserToken(User user, String jwtToken) {
    Token token = new Token(jwtToken, TokenType.BEARER, false, false,
      user);
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUserId(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }
}
