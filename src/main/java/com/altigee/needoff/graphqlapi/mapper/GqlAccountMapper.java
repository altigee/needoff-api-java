package com.altigee.needoff.graphqlapi.mapper;

import com.altigee.needoff.auth.model.Account;
import com.altigee.needoff.auth.model.Role;
import com.altigee.needoff.graphqlapi.model.GqlAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface GqlAccountMapper {

  GqlAccountMapper INSTANCE = Mappers.getMapper(GqlAccountMapper.class);

  @Mapping(source = "roles", target = "roles")
  GqlAccount toGraphQL(Account model);

  default List<String> toRolesFromRoles(List<Role> roles) {
    return roles.stream().map(Role::getName).map(Enum::name).collect(Collectors.toList());
  }
}
