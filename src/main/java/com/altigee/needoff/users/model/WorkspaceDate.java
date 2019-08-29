package com.altigee.needoff.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "workspace_id", name = "date")})
public class WorkspaceDate {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "workspace_id", nullable = false)
  private Workspace workspace;

  private LocalDate date;

  @Enumerated(EnumType.STRING)
  private Type type;

  public enum Type {
    HOLIDAY,
    WORKING_WEEKEND
  }
}
