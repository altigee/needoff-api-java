package com.altigee.needoff.auth.service;

import com.altigee.needoff.auth.exception.ExpiredTokenException;
import com.altigee.needoff.auth.exception.InvalidTokenException;
import com.altigee.needoff.auth.model.Account;
import com.altigee.needoff.auth.dto.ParsedToken;

public interface JwtService {

  String AUTHORITIES_KEY = "authorities";

  String newAccessToken(Account account, String jti);
  String newRefreshToken(Account account, String jti);


  ParsedToken parseToken(String token) throws InvalidTokenException, ExpiredTokenException;
}
