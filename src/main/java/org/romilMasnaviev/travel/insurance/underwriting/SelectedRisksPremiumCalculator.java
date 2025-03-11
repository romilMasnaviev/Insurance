package org.romilMasnaviev.travel.insurance.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.RiskPremium;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class SelectedRisksPremiumCalculator {
    private final List<TravelRiskPremiumCalculator> calculators;

    List<RiskPremium> calculatePremiumsForAllRisks(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks().stream()
                .map(riskIc ->
                        new RiskPremium(riskIc, calculatePremiumForRisk(riskIc, request))
                ).toList();
    }

    private BigDecimal calculatePremiumForRisk(String riskIc, TravelCalculatePremiumRequest request) {
        var riskPremiumCalculator = findRiskPremiumCalculator(riskIc);
        return riskPremiumCalculator.calculatePremium(request);
    }

    private TravelRiskPremiumCalculator findRiskPremiumCalculator(String riskIc) {
        return calculators.stream().
                filter(c -> c.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported riskIc = " + riskIc));
    }
}
