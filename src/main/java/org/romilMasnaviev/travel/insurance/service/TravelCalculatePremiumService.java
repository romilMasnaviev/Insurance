package org.romilMasnaviev.travel.insurance.service;

import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.TravelCalculatePremiumResponse;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request);

}
