package org.romilMasnaviev.travel.insurance.underwriting;

import lombok.Getter;
import org.romilMasnaviev.travel.insurance.dto.response.RiskPremium;

import java.math.BigDecimal;
import java.util.List;

@Getter
public record TravelCalculationResult(BigDecimal totalPremium,
                                      List<RiskPremium> riskPremiums) {
}
