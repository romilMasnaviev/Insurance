package org.javaguru.travel.insurance.service;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {

    private final DateTimeService service;

    TravelPremiumUnderwritingImpl(DateTimeService service) {
        this.service = service;
    }

    public BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request) {
        return service.calculateDaysBetweenDates(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
}
