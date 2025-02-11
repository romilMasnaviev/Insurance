package org.javaguru.travel.insurance.controller;

import com.google.common.base.Stopwatch;
import org.javaguru.travel.insurance.controller.logger.TravelCalculateExecutionTimeLogger;
import org.javaguru.travel.insurance.controller.logger.TravelCalculateRequestLogger;
import org.javaguru.travel.insurance.controller.logger.TravelCalculateResponseLogger;
import org.javaguru.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.response.TravelCalculatePremiumResponse;
import org.javaguru.travel.insurance.service.api.TravelCalculatePremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel")
class TravelCalculatePremiumController {

    private final TravelCalculatePremiumService calculatePremiumService;
    private final TravelCalculateRequestLogger requestLogger;
    private final TravelCalculateResponseLogger responseLogger;
    private final TravelCalculateExecutionTimeLogger timeLogger;

    @Autowired
    TravelCalculatePremiumController(TravelCalculatePremiumService calculatePremiumService,
                                     TravelCalculateRequestLogger requestLogger,
                                     TravelCalculateResponseLogger responseLogger,
                                     TravelCalculateExecutionTimeLogger timeLogger) {
        this.calculatePremiumService = calculatePremiumService;
        this.requestLogger = requestLogger;
        this.responseLogger = responseLogger;
        this.timeLogger = timeLogger;
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
        Stopwatch stopWatch = Stopwatch.createStarted();
        TravelCalculatePremiumResponse response = processRequest(request);
        timeLogger.log(stopWatch);
        return response;
    }


    private TravelCalculatePremiumResponse processRequest(TravelCalculatePremiumRequest request) {
        requestLogger.log(request);
        TravelCalculatePremiumResponse response = calculatePremiumService.calculatePremium(request);
        responseLogger.log(response);
        return response;
    }
}