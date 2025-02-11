package org.javaguru.travel.insurance.service.api;

import java.math.BigDecimal;
import java.util.Date;

public interface DateTimeService {

    BigDecimal calculateDaysBetweenDates(Date firstDate, Date secondDate);

}
