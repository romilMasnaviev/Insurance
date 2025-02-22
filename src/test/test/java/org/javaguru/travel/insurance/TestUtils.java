package org.romilMasnaviev.travel.insurance;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TestUtils {

    public static Date futureDate(int days) {
        return Date.from(LocalDate.now().plusDays(days).atStartOfDay(ZoneId.of("UTC")).toInstant());
    }

    public static Date pastDate(int days) {
        return Date.from(LocalDate.now().minusDays(days).atStartOfDay(ZoneId.of("UTC")).toInstant());
    }
}
