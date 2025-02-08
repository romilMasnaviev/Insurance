package org.javaguru.travel.insurance.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class TravelCalculateRequestLogger {

    private final Logger logger = LoggerFactory.getLogger(TravelCalculateRequestLogger.class);
    private final ObjectMapper mapper = new ObjectMapper();

    void log(TravelCalculatePremiumRequest request) {
        try {
            logger.info("method calculatePremium, request = {}", mapper.writeValueAsString(request));
        } catch (JsonProcessingException ex) {
            logger.error("method calculatePremium, error serializing request = {}", request);
        }
    }
}
