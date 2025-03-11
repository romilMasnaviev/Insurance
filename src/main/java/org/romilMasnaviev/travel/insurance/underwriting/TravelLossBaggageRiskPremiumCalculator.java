package org.romilMasnaviev.travel.insurance.underwriting;

import org.springframework.stereotype.Component;

@Component
class TravelLossBaggageRiskPremiumCalculator implements TravelRiskPremiumCalculator {
    @Override
    public String getRiskIc() {
        return "TRAVEL_LOSS_BAGGAGE";
    }
}
