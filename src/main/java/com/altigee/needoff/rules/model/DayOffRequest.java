package com.altigee.needoff.rules.model;

import com.altigee.needoff.balance.model.DayOff;
import lombok.Data;

import java.time.LocalDate;

/**
 * this class is created just for experiments
 * TODO: clean it up
 */
@Data
public class DayOffRequest {
    private boolean officialHoliday;
    private LocalDate date;
    private DayOff dayOff;

}
