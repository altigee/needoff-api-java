package com.altigee.needoff.graphqlapi.query;

import com.altigee.needoff.graphqlapi.error.GqlProfileNotFoundError;
import com.altigee.needoff.graphqlapi.mapper.GqlUserProfileMapper;
import com.altigee.needoff.graphqlapi.model.GqlUserProfile;
import com.altigee.needoff.users.service.ProfileService;
import com.altigee.needoff.shared.util.Ctx;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryResolver implements GraphQLQueryResolver {
  private ProfileService profileService;

  @Autowired
  public QueryResolver(ProfileService profileService) {
    this.profileService = profileService;
  }

  public GqlUserProfile getProfile() {
    return profileService.getProfile(Ctx.currentUserId())
        .map(GqlUserProfileMapper.INSTANCE::userProfileToGraphQL)
        .orElseThrow(GqlProfileNotFoundError::new);
  }
}
