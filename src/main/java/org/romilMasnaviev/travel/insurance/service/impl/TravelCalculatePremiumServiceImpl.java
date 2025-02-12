package org.romilMasnaviev.travel.insurance.service.impl;

import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.TravelCalculatePremiumResponse;
import org.romilMasnaviev.travel.insurance.dto.response.ValidationError;
import org.romilMasnaviev.travel.insurance.service.api.TravelCalculatePremiumService;
import org.romilMasnaviev.travel.insurance.service.api.TravelPremiumUnderwriting;
import org.romilMasnaviev.travel.insurance.validation.api.TravelCalculatePremiumRequestValidator;
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
