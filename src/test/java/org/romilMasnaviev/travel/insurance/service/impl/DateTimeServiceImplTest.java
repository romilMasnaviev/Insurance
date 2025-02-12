package org.romilMasnaviev.travel.insurance.service.impl;

import org.romilMasnaviev.travel.insurance.service.api.DateTimeService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.romilMasnaviev.travel.insurance.TestUtils.futureDate;

class DateTimeServiceImplTest {

    private final DateTimeService service = new DateTimeServiceImpl();

    @Test
    void testCalculateDaysBetweenDates_WhenStartDateBeforeEndDate_ThenReturnPositiveDays() {
        Date startDate = futureDate(10);
        Date endDate = futureDate(29);

        BigDecimal days = service.calculateDaysBetweenDates(startDate, endDate);
        assertThat(days).isEqualTo(BigDecimal.valueOf(19L));
    }

    @Test
    void testCalculateDaysBetweenDates_WhenStartDateEqualsEndDate_ThenReturnZeroDays() {
        Date startDate = futureDate(0);
        Date endDate = futureDate(0);

        BigDecimal days = service.calculateDaysBetweenDates(startDate, endDate);
        assertThat(days).isEqualTo(BigDecimal.valueOf(0L));
    }

    @Test
    void testCalculateDaysBetweenDates_WhenStartDateAfterEndDate_ThenReturnNegativeDays() {
        Date startDate = futureDate(29);
        Date endDate = futureDate(10);

        BigDecimal days = service.calculateDaysBetweenDates(startDate, endDate);
        assertThat(days).isEqualTo(BigDecimal.valueOf(-19L));
    }

}