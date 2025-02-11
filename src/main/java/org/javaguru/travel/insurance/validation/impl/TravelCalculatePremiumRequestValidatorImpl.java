package org.javaguru.travel.insurance.validation.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.response.ValidationError;
import org.javaguru.travel.insurance.validation.api.TravelCalculatePremiumRequestValidator;
import org.javaguru.travel.insurance.validation.validators.RequestValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidator {

    private List<RequestValidation> travelValidations;

    @Autowired
    public TravelCalculatePremiumRequestValidatorImpl(List<RequestValidation> travelValidations) {
        this.travelValidations = travelValidations;
    }

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();

        for (RequestValidation validation : travelValidations) {
            validation.execute(request).ifPresent(errors::add);
        }
        return errors;
    }

}
