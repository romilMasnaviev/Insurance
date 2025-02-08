package org.javaguru.travel.insurance.service;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelPremiumUnderwriting underwriting;
    private final TravelCalculatePremiumRequestValidator validator;

    @Autowired
    TravelCalculatePremiumServiceImpl(TravelPremiumUnderwriting underwriting, TravelCalculatePremiumRequestValidator validator) {
        this.underwriting = underwriting;
        this.validator = validator;
    }

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = validator.validate(request);

        return errors.isEmpty() ?
                buildResponse(request, underwriting.calculateAgreementPrice(request)) :
                buildResponse(errors);
    }

    private TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

    private TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request,
                                                         BigDecimal price) {
        return TravelCalculatePremiumResponse.builder()
                .agreementDateFrom(request.getAgreementDateFrom())
                .agreementDateTo(request.getAgreementDateTo())
                .personFirstName(request.getPersonFirstName())
                .personLastName(request.getPersonLastName())
                .agreementPrice(price)
                .build();
    }

}
