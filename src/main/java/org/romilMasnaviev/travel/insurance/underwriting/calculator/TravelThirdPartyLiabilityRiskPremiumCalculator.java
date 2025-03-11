package org.romilMasnaviev.travel.insurance.underwriting.calculator;

import org.romilMasnaviev.travel.insurance.underwriting.TravelRiskPremiumCalculator;
import org.springframework.stereotype.Component;

@Component
class TravelThirdPartyLiabilityRiskPremiumCalculator implements TravelRiskPremiumCalculator {
    @Override
    public String getRiskIc() {
        return "TRAVEL_THIRD_PARTY_LIABILITY";
    }
}
