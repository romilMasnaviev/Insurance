package org.javaguru.travel.insurance.service.api;

import org.javaguru.travel.insurance.dto.request.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public interface TravelPremiumUnderwriting {

    BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request);
}
