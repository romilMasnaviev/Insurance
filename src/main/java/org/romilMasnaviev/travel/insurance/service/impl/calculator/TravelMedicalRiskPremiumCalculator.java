package org.romilMasnaviev.travel.insurance.service.impl.calculator;

import org.romilMasnaviev.travel.insurance.service.api.TravelRiskPremiumCalculator;
import org.springframework.stereotype.Component;

@Component
public class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {
    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}
