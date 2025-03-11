package org.romilMasnaviev.travel.insurance.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.RiskPremium;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {

    private final List<TravelRiskPremiumCalculator> calculators;

    public TravelCalculationResult calculatePremium(TravelCalculatePremiumRequest request) {
        List<RiskPremium> riskPremiums = request.getSelectedRisks().stream()
                .map(riskIc ->
                        new RiskPremium(riskIc, calculatePremiumForRisk(riskIc, request))
                ).toList();

        BigDecimal totalPremium = riskPremiums.stream()
                .map(RiskPremium::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new TravelCalculationResult(totalPremium, riskPremiums);
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
