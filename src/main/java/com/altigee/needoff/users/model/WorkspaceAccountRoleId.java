package com.altigee.needoff.users.model;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
public class WorkspaceAccountRoleId implements Serializable {

  private Long workspaceId;
  private Long accountId;
  @Enumerated(EnumType.STRING)
  private WorkspaceAccountRole.Role role;


}
