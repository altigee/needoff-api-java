package com.altigee.needoff.users.data;

import com.altigee.needoff.users.model.WorkspaceAccountRole;
import com.altigee.needoff.users.model.WorkspaceAccountRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkspaceAccountRoleRepo
    extends JpaRepository<WorkspaceAccountRole, WorkspaceAccountRoleId> {

  List<WorkspaceAccountRole> findByWorkspaceAccountRoleId(
      WorkspaceAccountRoleId workspaceAccountRoleId);

  List<WorkspaceAccountRole>
      findDistinctByWorkspaceAccountRoleId_WorkspaceIdAndWorkspaceAccountRoleId_AccountId(
          Long workspaceId, Long accountId);

  List<WorkspaceAccountRole>
      findDistinctByWorkspaceAccountRoleId_WorkspaceIdAndWorkspaceAccountRoleId_Role(
          Long workspaceId, WorkspaceAccountRole.Role role);
}
