package org.romilMasnaviev.travel.insurance.service.impl;

import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.service.api.DateTimeService;
import org.romilMasnaviev.travel.insurance.service.api.TravelPremiumUnderwriting;
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
