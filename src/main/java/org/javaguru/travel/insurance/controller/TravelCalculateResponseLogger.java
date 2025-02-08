package org.javaguru.travel.insurance.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class TravelCalculateResponseLogger {
    Logger logger = LoggerFactory.getLogger(TravelCalculateResponseLogger.class);
    ObjectMapper mapper = new ObjectMapper();

    void log(TravelCalculatePremiumResponse request) {
        try {
            logger.info("method calculatePremium, response = {}", mapper.writeValueAsString(request));
        } catch (JsonProcessingException ex) {
            logger.error("method calculatePremium, error serializing response = {}", request);
        }
    }
}
