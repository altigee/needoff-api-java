package com.altigee.needoff.graphqlapi.mapper;

import com.altigee.needoff.graphqlapi.model.GqlWorkspace;
import com.altigee.needoff.users.model.Workspace;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GqlWorkspaceMapper {

  GqlWorkspaceMapper INSTANCE = Mappers.getMapper(GqlWorkspaceMapper.class);

  GqlWorkspace workspaceToGraphQL(Workspace model);


}
