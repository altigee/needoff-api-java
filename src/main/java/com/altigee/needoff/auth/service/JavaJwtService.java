package com.altigee.needoff.auth.service;

import com.altigee.needoff.auth.dto.ParsedToken;
import com.altigee.needoff.auth.exception.ExpiredTokenException;
import com.altigee.needoff.auth.exception.InvalidTokenException;
import com.altigee.needoff.auth.model.Account;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class JavaJwtService implements JwtService {
  @Value("${needoff.jwt.accessTokenExpirationMinutes}")
  private int accessTokenExpirationMinutes;
  @Value("${needoff.jwt.refreshTokenExpirationMinutes}")
  private int refreshTokenExpirationMinutes;
  @Value("${needoff.jwt.secretKey}")
  private String secretKey;

  @Override
  public String newAccessToken(Account account, String jti) {
    return Jwts.builder()
        .setSubject(account.getId().toString())
        .setIssuedAt(new Date())
        .setExpiration(newExpiration(accessTokenExpirationMinutes))
        .claim("jti", jti)
        .claim(JwtService.AUTHORITIES_KEY, RolesUtil.rolesToString(account.getRoles()))
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  @Override
  public String newRefreshToken(Account account, String jti) {
    return Jwts.builder()
        .setSubject(account.getId().toString())
        .setIssuedAt(new Date())
        .setExpiration(newExpiration(refreshTokenExpirationMinutes))
        .claim("jti", jti)
        .claim(AUTHORITIES_KEY, RolesUtil.rolesToString(account.getRoles()))
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  @Override
  public ParsedToken parseToken(String token) throws InvalidTokenException, ExpiredTokenException {
    try {
      var body = Jwts.parser()
          .setSigningKey(secretKey)
          .parseClaimsJws(token)
          .getBody();
      return ParsedToken.builder()
          .subject(body.getSubject())
          .issuedAt(body.getIssuedAt())
          .expiresAt(body.getExpiration())
          .jti(body.get("jti", String.class))
          .authorities(RolesUtil.stringToAuthorities(body.get(AUTHORITIES_KEY, String.class)))
          .build();
    } catch (ExpiredJwtException e) {
      throw new ExpiredTokenException("Token has expired");
    } catch (SignatureException | IllegalArgumentException e) {
      throw new InvalidTokenException("Token signature is invalid or the token is malformed");
    }
  }

  private Date newExpiration(int minutes) {
    Calendar calender = Calendar.getInstance();
    calender.add(Calendar.MINUTE, minutes);
    return calender.getTime();
  }



}
