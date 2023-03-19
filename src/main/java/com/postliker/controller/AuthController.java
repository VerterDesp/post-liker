package com.postliker.controller;

import com.postliker.auth.AuthResponse;
import com.postliker.auth.LoginRequest;
import com.postliker.auth.RegisterRequest;
import com.postliker.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService service;

  public AuthController(AuthService service) {
    this.service = service;
  }

  @ResponseStatus(HttpStatus.OK)
  @PostMapping("/register")
  public void register(@RequestBody @Valid RegisterRequest request) {
    service.register(request);
  }

  @ResponseStatus(HttpStatus.OK)
  @PostMapping("/login")
  public AuthResponse authenticate(@RequestBody @Valid LoginRequest request) {
    return service.login(request);
  }
}
