package com.altigee.needoff.users.service;

import java.time.LocalDate;

public interface WorkspaceDateService {
  Long getWorkingDaysInDateRange(LocalDate fromDate, LocalDate toDate, Long workspaceId);
}
