package org.romilMasnaviev.travel.insurance.controller;

import com.google.common.base.Stopwatch;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.TravelCalculatePremiumResponse;
import org.romilMasnaviev.travel.insurance.service.TravelCalculatePremiumService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumController {

    private final TravelCalculatePremiumService calculatePremiumService;
    private final TravelCalculateRequestLogger requestLogger;
    private final TravelCalculateResponseLogger responseLogger;
    private final TravelCalculateExecutionTimeLogger timeLogger;

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