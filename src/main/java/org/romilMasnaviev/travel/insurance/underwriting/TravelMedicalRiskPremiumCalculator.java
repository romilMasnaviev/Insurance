package org.romilMasnaviev.travel.insurance.underwriting;

import org.springframework.stereotype.Component;

@Component
class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {
    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}
