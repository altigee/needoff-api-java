package com.altigee.needoff.auth.config;

import com.altigee.needoff.auth.service.JwtService;
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
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTH_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    public static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain chain) throws IOException, ServletException {

        getAuthToken(request).map(this::decodeAuthentication).ifPresent(
            SecurityContextHolder.getContext()::setAuthentication);

        chain.doFilter(request, response);
    }

    private Optional<String> getAuthToken(
        HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(AUTH_HEADER))
            .filter(header -> header.startsWith(BEARER_PREFIX))
            .map(header -> header.replace(BEARER_PREFIX, ""));
    }

    private UsernamePasswordAuthenticationToken decodeAuthentication(String authToken) {
        var jwt = jwtService.parseToken(authToken);
        var id = Long.parseLong(jwt.getSubject());
        var authorities = jwt.getAuthorities();
        return new UsernamePasswordAuthenticationToken(id, null, authorities);
    }
}
