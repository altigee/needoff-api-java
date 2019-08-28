package com.altigee.needoff.users.data;

import com.altigee.needoff.users.model.WorkspaceInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceInvitationRepo extends JpaRepository<WorkspaceInvitation,Long> {
}
