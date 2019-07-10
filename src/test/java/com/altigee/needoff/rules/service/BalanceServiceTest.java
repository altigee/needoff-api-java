package com.altigee.needoff.rules.service;

import com.altigee.needoff.balance.model.DayOff;
import com.altigee.needoff.balance.model.LeaveType;
import com.altigee.needoff.rules.model.DayOffBalance;
import com.altigee.needoff.rules.model.DayOffRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BalanceServiceTest {

    @Autowired
    BalanceService balanceService;

    @Test
    public void requestDayOffTest() {
        DayOffRequest request = new DayOffRequest();
        LocalDate monday = LocalDate.now().with(DayOfWeek.MONDAY);
        request.setDate(monday);
        request.setOfficialHoliday(Boolean.FALSE);

        DayOff dayOff = new DayOff();
        dayOff.setLeaveType(LeaveType.VACATION_PAID);
        request.setDayOff(dayOff);

        DayOffBalance balance = balanceService.requestDayOff(request, new DayOffBalance());
        assertNotNull(balance);
        assertEquals(1, balance.getUsedPaidLeaves());
    }
}
