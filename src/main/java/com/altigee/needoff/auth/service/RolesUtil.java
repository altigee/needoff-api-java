package com.altigee.needoff.auth.service;

import com.altigee.needoff.auth.model.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RolesUtil {

  public static String rolesToString(Set<Role> roles) {
    return Optional.ofNullable(roles).map(RolesUtil::toString).orElse("");
  }

  private static String toString(Set<Role> roles) {
    return roles.stream().map(Role::getName).map(Role.Type::name).collect(Collectors.joining(","));
  }

  public static Set<? extends GrantedAuthority> stringToAuthorities(String roles) {
    return Optional.ofNullable(roles).map(RolesUtil::parseRoles).orElse(Collections.emptySet());
  }

  private static Set<? extends GrantedAuthority> parseRoles(String roles) {
    return Stream.of(roles.split(","))
        .filter(StringUtils::isNotBlank)
        .map(roleName -> String.join("_", "ROLE", roleName))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toSet());
  }
}
