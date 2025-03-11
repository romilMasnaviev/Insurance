package org.romilMasnaviev.travel.insurance.underwriting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.romilMasnaviev.travel.insurance.dto.response.RiskPremium;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TravelCalculationResult {
    private BigDecimal totalPremium;
    private List<RiskPremium> riskPremiums;

}
