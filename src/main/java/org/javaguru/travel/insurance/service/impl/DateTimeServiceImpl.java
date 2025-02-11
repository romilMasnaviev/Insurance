package org.javaguru.travel.insurance.service.impl;

import org.javaguru.travel.insurance.service.api.DateTimeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
class DateTimeServiceImpl implements DateTimeService {

    public BigDecimal calculateDaysBetweenDates(Date firstDate, Date secondDate) {
        return (firstDate != null && secondDate != null) ?
                BigDecimal.valueOf(ChronoUnit.DAYS.between(firstDate.toInstant(), secondDate.toInstant())) :
                null;
    }
}
