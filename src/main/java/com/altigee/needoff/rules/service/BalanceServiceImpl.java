package com.altigee.needoff.rules.service;

import com.altigee.needoff.rules.model.DayOffBalance;
import com.altigee.needoff.rules.model.DayOffRequest;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BalanceServiceImpl implements  BalanceService {

    @Autowired
    private KieContainer kieContainer;

    @Override
    public DayOffBalance requestDayOff(DayOffRequest request, DayOffBalance balance) {

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("dayOffBalance", balance);
        kieSession.insert(request);
        kieSession.fireAllRules();
        kieSession.dispose();

        log.debug("day-off request: {}", request);
        log.debug("day-off balance: {}", balance);
        return balance;
    }
}
