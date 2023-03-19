package com.postliker.config;

import com.postliker.model.Token;
import com.postliker.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class LogoutService implements LogoutHandler {

  private final TokenRepository tokenRepository;

  public LogoutService(TokenRepository tokenRepository) {
    this.tokenRepository = tokenRepository;
  }

  @Override
  public void logout(HttpServletRequest request,
                     HttpServletResponse response,
                     Authentication authentication) {
    final String authHeader = request.getHeader("Authorization");
    final String jwt;

    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }

    jwt = authHeader.substring(7);
    Token storedToken = tokenRepository.findByToken(jwt).orElse(null);

    if (storedToken != null) {
      storedToken.setExpired(true);
      storedToken.setRevoked(true);
      tokenRepository.save(storedToken);
      SecurityContextHolder.clearContext();
    }
  }
}
