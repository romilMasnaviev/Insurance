package org.javaguru.travel.insurance.service;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public interface TravelPremiumUnderwriting {

    BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request);
}
