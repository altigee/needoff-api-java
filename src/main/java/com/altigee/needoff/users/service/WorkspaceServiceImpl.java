package com.altigee.needoff.users.service;

import com.altigee.needoff.users.data.WorkspaceRepo;
import com.altigee.needoff.users.model.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

  @Autowired private WorkspaceRepo workspaceRepo;

  @Override public Optional<Workspace> get(Long id) {
    return workspaceRepo.findById(id);
  }

  @Override
  public Workspace save(Workspace workspace) {
    return workspaceRepo.save(workspace);
  }
}
