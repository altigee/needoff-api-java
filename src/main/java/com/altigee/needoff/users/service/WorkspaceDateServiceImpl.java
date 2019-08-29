package com.altigee.needoff.users.service;

import com.altigee.needoff.users.data.WorkspaceDateRepo;
import com.altigee.needoff.users.model.WorkspaceDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class WorkspaceDateServiceImpl implements WorkspaceDateService {

  @Autowired WorkspaceDateRepo workspaceDateRepo;

  @Override
  public Long getWorkingDaysInDateRange(LocalDate fromDate, LocalDate toDate, Long workspaceId) {

    List<WorkspaceDate> workspaceDates =
        workspaceDateRepo.findAllByDateStartsWithAndDateEndingWithAndWorkspace_Id(
            fromDate, toDate, workspaceId);

    Set<LocalDate> holidays =
        workspaceDates.stream()
            .filter(date -> WorkspaceDate.Type.HOLIDAY.equals(date.getType()))
            .map(WorkspaceDate::getDate)
            .collect(Collectors.toSet());

    Set<LocalDate> workingWeekendDays =
        workspaceDates.stream()
            .filter(date -> WorkspaceDate.Type.WORKING_WEEKEND.equals(date.getType()))
            .map(WorkspaceDate::getDate)
            .collect(Collectors.toSet());

    Predicate<LocalDate> isHoliday = day -> holidays.contains(day);

    Predicate<LocalDate> isWeekend =
          day ->
              (DayOfWeek.SATURDAY.equals(day.getDayOfWeek())
                  || DayOfWeek.SUNDAY.equals(day.getDayOfWeek()));

    Predicate<LocalDate> isWorkingDay =
        day -> workingWeekendDays.contains(day);

    Long workingDays =
        fromDate.datesUntil(toDate.plusDays(1))
            .filter(isHoliday.negate())
            .filter(isWeekend.and(isWorkingDay.negate()).negate())
            .count();

    return workingDays;
  }
}
