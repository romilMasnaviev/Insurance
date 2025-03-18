package org.romilMasnaviev.travel.insurance.underwriting;

import org.romilMasnaviev.travel.insurance.dto.response.RiskPremium;

import java.math.BigDecimal;
import java.util.List;

public record TravelCalculationResult(BigDecimal totalPremium,
                                      List<RiskPremium> riskPremiums) {
}
