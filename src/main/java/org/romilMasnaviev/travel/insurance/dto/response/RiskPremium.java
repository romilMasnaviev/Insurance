package org.romilMasnaviev.travel.insurance.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RiskPremium {
    private String riskIc;
    private BigDecimal premium;
}
