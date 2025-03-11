package org.romilMasnaviev.travel.insurance.underwriting;

import org.springframework.stereotype.Component;

@Component
class TravelSportActivitiesRiskPremiumCalculator implements TravelRiskPremiumCalculator {
    @Override
    public String getRiskIc() {
        return "TRAVEL_SPORT_ACTIVITIES";
    }
}
