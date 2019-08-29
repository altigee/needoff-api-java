package com.altigee.needoff.users.data;

import com.altigee.needoff.users.model.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepo extends JpaRepository<Workspace, Long> {
}
