package com.altigee.needoff.users.service;

import com.altigee.needoff.users.data.WorkspaceDateRepo;
import com.altigee.needoff.users.model.WorkspaceDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class WorkspaceDateServiceTest {

  @MockBean WorkspaceDateRepo workspaceDateRepo;

  @Autowired WorkspaceDateService workspaceDateService;

  @TestConfiguration
  static class WorkspaceDateServiceTestContextConfiguration {

    @Bean
    public WorkspaceDateService workspaceDateService() {
      return new WorkspaceDateServiceImpl();
    }
  }

  @Test
  public void getWorkingDaysInDateRangeWithoutHolidays() {
    when(workspaceDateRepo.findAllByDateStartsWithAndDateEndingWithAndWorkspace_Id(
            any(), any(), any()))
        .thenReturn(Collections.emptyList());
    Long workingDays =
        workspaceDateService.getWorkingDaysInDateRange(
            LocalDate.of(2019, 8, 14), LocalDate.of(2019, 8, 20), 1L);
    assertEquals(Long.valueOf(5), workingDays);
  }

  @Test
  public void getWorkingDaysInDateRangeWithOneHoliday() {

    WorkspaceDate holiday = new WorkspaceDate();
    holiday.setDate(LocalDate.of(2019, 8, 20));
    holiday.setType(WorkspaceDate.Type.HOLIDAY);
    when(workspaceDateRepo.findAllByDateStartsWithAndDateEndingWithAndWorkspace_Id(
            any(), any(), any()))
        .thenReturn(Collections.singletonList(holiday));
    Long workingDays =
        workspaceDateService.getWorkingDaysInDateRange(
            LocalDate.of(2019, 8, 14), LocalDate.of(2019, 8, 20), 1L);

    assertEquals(Long.valueOf(4), workingDays);
  }

  @Test
  public void getWorkingDaysInDateRangeWithOneExtraWorkingDay() {

    WorkspaceDate holiday = new WorkspaceDate();
    holiday.setDate(LocalDate.of(2019, 8, 18));
    holiday.setType(WorkspaceDate.Type.WORKING_WEEKEND);
    when(workspaceDateRepo.findAllByDateStartsWithAndDateEndingWithAndWorkspace_Id(
            any(), any(), any()))
        .thenReturn(Collections.singletonList(holiday));
    Long workingDays =
        workspaceDateService.getWorkingDaysInDateRange(
            LocalDate.of(2019, 8, 14), LocalDate.of(2019, 8, 20), 1L);

    assertEquals(Long.valueOf(6), workingDays);
  }
}
