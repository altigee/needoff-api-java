package com.altigee.needoff.graphqlapi.mutation;

import com.altigee.needoff.graphqlapi.error.GqlProfileNotFoundError;
import com.altigee.needoff.graphqlapi.mapper.GqlUpdateProfileMapper;
import com.altigee.needoff.graphqlapi.mapper.GqlUserProfileMapper;
import com.altigee.needoff.graphqlapi.model.GqlUpdateProfile;
import com.altigee.needoff.graphqlapi.model.GqlUserProfile;
import com.altigee.needoff.users.exception.UserProfileNotFoundException;
import com.altigee.needoff.users.service.ProfileService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MutationResolver implements GraphQLMutationResolver {
  private ProfileService profileService;

  @Autowired
  public MutationResolver(ProfileService profileService) {
    this.profileService = profileService;
  }

  public GqlUserProfile updateProfile(GqlUpdateProfile update) {
    try {
      var request = GqlUpdateProfileMapper.INSTANCE.graphQLToUserProfile(update);
      var profile = profileService.updateProfile(10L, request);
      return GqlUserProfileMapper.INSTANCE.userProfileToGraphQL(profile);
    } catch (UserProfileNotFoundException upe) {
      throw new GqlProfileNotFoundError();
    }
  }
}
