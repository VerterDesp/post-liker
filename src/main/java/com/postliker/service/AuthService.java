package com.postliker.service;

import com.postliker.auth.AuthResponse;
import com.postliker.auth.LoginRequest;
import com.postliker.auth.RegisterRequest;

public interface AuthService {

  AuthResponse register(RegisterRequest request);

  AuthResponse login(LoginRequest request);
}
