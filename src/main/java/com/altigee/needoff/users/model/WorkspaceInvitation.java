package com.altigee.needoff.users.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class WorkspaceInvitation {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String email;

  @Enumerated(EnumType.STRING)
  private Status status;

  private LocalDate startDate;

  @ManyToOne
  @JoinColumn(name = "workspace_id", nullable = false)
  private Workspace workspace;

  enum Status {
    ACCEPTED,
    PENDING,
    REVOKED
  }
}
