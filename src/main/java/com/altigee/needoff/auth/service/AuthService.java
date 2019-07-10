package com.altigee.needoff.auth.service;

import com.altigee.needoff.auth.dto.*;
import com.altigee.needoff.auth.exception.*;

public interface AuthService {
  RegisterResponse register(RegisterRequest request) throws AccountExistsException;
  LoginResponse login(LoginRequest request) throws AccountDoesNotExistException, WrongCredentialsException;
  RefreshTokenResponse refreshToken(RefreshTokenRequest request) throws AccountDoesNotExistException, ExpiredTokenException, InvalidTokenException;
}
