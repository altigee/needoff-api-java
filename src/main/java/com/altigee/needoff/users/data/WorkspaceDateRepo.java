package com.altigee.needoff.users.data;

import com.altigee.needoff.users.model.WorkspaceDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkspaceDateRepo extends JpaRepository<WorkspaceDate, Long> {

  List<WorkspaceDate> findAllByDateStartsWithAndDateEndingWithAndWorkspace_Id(
      LocalDate fromDate, LocalDate toDate, Long workspaceId);
}
