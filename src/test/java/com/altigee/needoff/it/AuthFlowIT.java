package com.altigee.needoff.it;

import com.altigee.needoff.auth.dto.LoginRequest;
import com.altigee.needoff.auth.dto.RefreshTokenRequest;
import com.altigee.needoff.auth.dto.RegisterRequest;
import com.altigee.needoff.auth.exception.*;
import com.altigee.needoff.auth.service.AuthService;
import com.altigee.needoff.auth.service.JwtService;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = {AuthFlowIT.Init.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthFlowIT {
  private static String JWT_SECRET = "secret";
  private static String SAMPLE_EMAIL = "developer@altigee.com";
  private static String SAMPLE_PASSWORD = "12345678";

  @ClassRule
  public static MySQLContainer mysql = (MySQLContainer) new MySQLContainer("mysql:8.0")
      .withEnv("MYSQL_ROOT_PASSWORD", "altigee")
      .withEnv("MYSQL_DATABASE", "needoff")
      .withExposedPorts(3306);

  static class Init implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
      TestPropertyValues.of(
          "spring.datasource.url=" + mysql.getJdbcUrl(),
          "spring.datasource.username=" + "root",
          "spring.datasource.password=" + mysql.getPassword(),
          "needoff.jwt.secretKey=" + JWT_SECRET
      ).applyTo(applicationContext.getEnvironment());
    }
  }

  @Autowired
  private AuthService authService;
  @Autowired
  private JwtService jwtService;

  @Test
  public void verifySuccessfulFlowFull() throws AccountExistsException, ExpiredTokenException, InvalidTokenException, AccountDoesNotExistException, WrongCredentialsException {
    var registerResponse = authService.register(RegisterRequest.builder()
        .email(SAMPLE_EMAIL)
        .password(SAMPLE_PASSWORD).build());

    var id = registerResponse.getId();
    Assert.assertNotNull(id);
    Assert.assertNotNull(registerResponse.getAccessToken());
    Assert.assertNotNull(registerResponse.getRefreshToken());

    var accessToken = jwtService.parseToken(registerResponse.getAccessToken());
    Assert.assertEquals((long) id, Long.parseLong(accessToken.getSubject()));
    var refreshToken = jwtService.parseToken(registerResponse.getRefreshToken());
    Assert.assertEquals((long) id, Long.parseLong(refreshToken.getSubject()));
    Assert.assertNotNull(refreshToken.getJti());

    var oldRefreshToken = refreshToken;

    var loginResponse = authService.login(LoginRequest.builder()
        .email(SAMPLE_EMAIL)
        .password(SAMPLE_PASSWORD).build());
    Assert.assertNotNull(loginResponse.getAccessToken());
    Assert.assertNotNull(loginResponse.getRefreshToken());
    Assert.assertNotEquals(loginResponse.getAccessToken(), registerResponse.getAccessToken());
    Assert.assertNotEquals(loginResponse.getRefreshToken(), registerResponse.getRefreshToken());

    accessToken = jwtService.parseToken(loginResponse.getAccessToken());
    Assert.assertEquals((long) id, Long.parseLong(accessToken.getSubject()));

    refreshToken = jwtService.parseToken(loginResponse.getRefreshToken());
    Assert.assertEquals((long) id, Long.parseLong(refreshToken.getSubject()));
    Assert.assertNotNull(refreshToken.getJti());
    Assert.assertNotEquals(refreshToken.getJti(), oldRefreshToken.getJti());

    oldRefreshToken = refreshToken;

    var refreshResponse = authService.refreshToken(RefreshTokenRequest.builder()
        .refreshToken(loginResponse.getRefreshToken()).build());
    Assert.assertNotNull(refreshResponse.getAccessToken());
    Assert.assertNotNull(refreshResponse.getRefreshToken());
    Assert.assertNotEquals(refreshResponse.getAccessToken(), loginResponse.getAccessToken());
    Assert.assertNotEquals(refreshResponse.getRefreshToken(), loginResponse.getRefreshToken());

    accessToken = jwtService.parseToken(refreshResponse.getAccessToken());
    Assert.assertEquals((long) id, Long.parseLong(accessToken.getSubject()));

    refreshToken = jwtService.parseToken(refreshResponse.getRefreshToken());
    Assert.assertEquals((long) id, Long.parseLong(refreshToken.getSubject()));
    Assert.assertNotNull(refreshToken.getJti());
    Assert.assertNotEquals(refreshToken.getJti(), oldRefreshToken.getJti());
  }
}
