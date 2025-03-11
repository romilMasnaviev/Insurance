package org.romilMasnaviev.travel.insurance.underwriting;

import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {
    default BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return BigDecimal.ZERO;
    }

    String getRiskIc();
}