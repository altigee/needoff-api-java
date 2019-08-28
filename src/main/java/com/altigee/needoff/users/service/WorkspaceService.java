package com.altigee.needoff.users.service;

import com.altigee.needoff.users.model.Workspace;

import java.util.Optional;

public interface WorkspaceService {
    Optional<Workspace> get(Long id);
    Workspace save(Workspace workspace);
}
