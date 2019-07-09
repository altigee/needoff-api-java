package com.altigee.needoff.balance.model;

import com.altigee.needoff.users.model.UserProfile;
import com.altigee.needoff.users.model.Workspace;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class DayOff {
  @Id
  private Long id;

  @JoinColumn(name = "user_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private UserProfile user;

  @JoinColumn(name = "approved_by_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private UserProfile userProfile;

  @JoinColumn(name = "workspace_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private Workspace workspace;

  private LeaveType leaveType;
  private LocalDate startDate;
  private LocalDate endDate;
  private String comment;
}
