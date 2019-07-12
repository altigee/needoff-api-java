package com.altigee.needoff.auth.config;

import com.altigee.needoff.auth.dto.ErrorDetails;
import com.altigee.needoff.auth.exception.ExpiredTokenException;
import com.altigee.needoff.auth.exception.InvalidTokenException;
import com.altigee.needoff.auth.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.InvalidPropertiesFormatException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
  public static final String AUTH_HEADER = "Authorization";
  public static final String BEARER_PREFIX = "Bearer";

  public static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

  @Autowired
  private JwtService jwtService;
  @Autowired
  private ObjectMapper serializer;

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return request.getServletPath().startsWith("/auth");
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    try {
      var header = request.getHeader(AUTH_HEADER);
      if (header == null || !header.startsWith(BEARER_PREFIX)) {
        throw new InvalidTokenException("Missing or invalid token");
      }
      var authToken = header.replace(BEARER_PREFIX, "");
      var jwt = jwtService.parseToken(authToken);
      var id = Long.parseLong(jwt.getSubject());
      var authentication = new UsernamePasswordAuthenticationToken(id, "", Collections.emptyList()); //todo maybe there's a better way
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (ExpiredTokenException | InvalidTokenException e) {
      var error = ErrorDetails.builder().message(e.getMessage()).build();
      response.setStatus(401);
      serializer.writeValue(response.getWriter(), error);
      return;
    }
    chain.doFilter(request, response);
  }

}
