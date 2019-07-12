package com.altigee.needoff.graphqlapi.mapper;

import com.altigee.needoff.graphqlapi.model.GqlUserProfile;
import com.altigee.needoff.users.model.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GqlUserProfileMapper {
  GqlUserProfileMapper INSTANCE = Mappers.getMapper(GqlUserProfileMapper.class);

  @Mapping(source = "account.id", target = "userId")
  GqlUserProfile userProfileToGraphQL(UserProfile model);
}
