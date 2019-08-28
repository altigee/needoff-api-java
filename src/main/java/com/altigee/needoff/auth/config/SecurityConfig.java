package com.altigee.needoff.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtUnauthorizedEntryPoint unauthorizedHandler;

  @Autowired
  public SecurityConfig(JwtUnauthorizedEntryPoint unauthorizedHandler) {
    this.unauthorizedHandler = unauthorizedHandler;
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.cors().and().csrf().disable()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .antMatcher("/graphql")
        .authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
    return new JwtAuthenticationFilter();
  }

  @Bean
  public FilterRegistrationBean filterRegistrationBean() throws Exception {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.setEnabled(false);
    filterRegistrationBean.setFilter(authenticationTokenFilterBean());
    return filterRegistrationBean;
  }

}
