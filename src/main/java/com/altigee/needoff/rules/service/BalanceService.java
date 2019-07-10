package com.altigee.needoff.rules.service;

import com.altigee.needoff.rules.model.DayOffBalance;
import com.altigee.needoff.rules.model.DayOffRequest;

public interface BalanceService {

    DayOffBalance requestDayOff(DayOffRequest request, DayOffBalance balance);
}
