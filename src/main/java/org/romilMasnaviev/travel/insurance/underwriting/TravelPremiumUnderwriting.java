package org.romilMasnaviev.travel.insurance.underwriting;

import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;

public interface TravelPremiumUnderwriting {

    TravelCalculationResult calculatePremium(TravelCalculatePremiumRequest request);
}
