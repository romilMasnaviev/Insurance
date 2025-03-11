package org.romilMasnaviev.travel.insurance.controller;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculateExecutionTimeLogger {
    Logger logger = LoggerFactory.getLogger(TravelCalculateExecutionTimeLogger.class);

    public void log(Stopwatch stopWatch) {
        stopWatch.stop();
        logger.info("Request processing time (ms): {}", stopWatch.elapsed().toMillis());
    }
}
