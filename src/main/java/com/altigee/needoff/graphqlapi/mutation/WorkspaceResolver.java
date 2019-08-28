package com.altigee.needoff.graphqlapi.mutation;

import com.altigee.needoff.graphqlapi.mapper.GqlWorkspaceMapper;
import com.altigee.needoff.graphqlapi.model.GqlCreateWorkspace;
import com.altigee.needoff.graphqlapi.model.GqlStatusResponse;
import com.altigee.needoff.users.model.Workspace;
import com.altigee.needoff.users.service.WorkspaceService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.function.Function;

@Component
@SuppressWarnings("unused")
public class WorkspaceResolver implements GraphQLMutationResolver{

    @Autowired
    private WorkspaceService workspaceService;

    @Secured("ROLE_ADMIN")
    public GqlCreateWorkspace createWorkspace(String description, String name) {
        var workspace =
            workspaceService.save(Workspace.builder().description(description).name(name).build());
        return GqlCreateWorkspace.builder()
            .ok(Boolean.TRUE)
            .ws(GqlWorkspaceMapper.INSTANCE.workspaceToGraphQL(workspace))
            .build();
    }

    public GqlStatusResponse updateWorkspace(String description, String name, Long workspaceId) {

        Function<Workspace, Workspace> updateWorkspaceFields =
            w -> {
                w.setName(name);
                w.setDescription(description);
                return w;
            };

        return workspaceService
            .get(workspaceId)
            .map(updateWorkspaceFields)
            .map(workspaceService::save)
            .map(m -> GqlStatusResponse.builder().ok(Boolean.TRUE).build())
            .orElse(GqlStatusResponse.builder().ok(Boolean.FALSE).build());
    }
}
