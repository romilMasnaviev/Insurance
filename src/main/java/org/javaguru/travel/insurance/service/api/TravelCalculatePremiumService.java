package org.javaguru.travel.insurance.service.api;

import org.javaguru.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.response.TravelCalculatePremiumResponse;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request);

}
