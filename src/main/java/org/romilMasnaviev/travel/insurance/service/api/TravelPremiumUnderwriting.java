package org.romilMasnaviev.travel.insurance.service.api;

import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public interface TravelPremiumUnderwriting {

    BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request);
}
