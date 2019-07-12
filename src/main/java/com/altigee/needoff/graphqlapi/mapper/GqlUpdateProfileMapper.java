package com.altigee.needoff.graphqlapi.mapper;

import com.altigee.needoff.graphqlapi.model.GqlUpdateProfile;
import com.altigee.needoff.users.dto.UpdateProfileRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GqlUpdateProfileMapper {
  GqlUpdateProfileMapper INSTANCE = Mappers.getMapper(GqlUpdateProfileMapper.class);

  UpdateProfileRequest graphQLToUserProfile(GqlUpdateProfile request);
}
