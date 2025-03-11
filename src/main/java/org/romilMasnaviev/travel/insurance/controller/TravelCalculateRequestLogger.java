package org.romilMasnaviev.travel.insurance.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculateRequestLogger {

    private final Logger logger = LoggerFactory.getLogger(TravelCalculateRequestLogger.class);
    private final ObjectMapper mapper = new ObjectMapper();

    public void log(TravelCalculatePremiumRequest request) {
        try {
            logger.info("method calculatePremium, request = {}", mapper.writeValueAsString(request));
        } catch (JsonProcessingException ex) {
            logger.error("method calculatePremium, error serializing request = {}", request);
        }
    }
}
