package com.altigee.needoff.auth.service;

import com.altigee.needoff.auth.dto.ParsedToken;
import com.altigee.needoff.auth.exception.ExpiredTokenException;
import com.altigee.needoff.auth.exception.InvalidTokenException;
import com.altigee.needoff.auth.model.Account;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class JavaJwtService implements JwtService {

  private String key = "1234";

  @Override
  public String newAccessToken(Account account) {
    return Jwts.builder()
        .setSubject(account.getId().toString())
        .setIssuedAt(new Date())
        .setExpiration(newExpiration())
        .signWith(SignatureAlgorithm.HS256, key)
        .compact();
  }

  @Override
  public String newRefreshToken(Account account, String jti) {
    return Jwts.builder()
        .setSubject(account.getId().toString())
        .setIssuedAt(new Date())
        .setExpiration(newExpiration())
        .claim("jti", jti)
        .signWith(SignatureAlgorithm.HS256, key)
        .compact();
  }

  @Override
  public ParsedToken parseToken(String token) throws InvalidTokenException, ExpiredTokenException {
    try {
      var body = Jwts.parser()
          .setSigningKey(key)
          .parseClaimsJws(token)
          .getBody();
      return ParsedToken.builder()
          .subject(body.getSubject())
          .issuedAt(body.getIssuedAt())
          .expiresAt(body.getExpiration())
          .jti(body.get("jti", String.class))
          .build();
    } catch (ExpiredJwtException e) {
      throw new ExpiredTokenException("Token has expired");
    } catch (SignatureException | IllegalArgumentException e) {
      throw new InvalidTokenException("Token signature is invalid or the token is malformed");
    }
  }

  private static Date newExpiration() {
    Calendar calender = Calendar.getInstance();
    calender.add(Calendar.MINUTE, 30);
    return calender.getTime();
  }
}
