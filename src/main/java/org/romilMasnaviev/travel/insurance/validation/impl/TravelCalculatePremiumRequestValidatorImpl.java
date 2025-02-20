package org.romilMasnaviev.travel.insurance.validation.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.ValidationError;
import org.romilMasnaviev.travel.insurance.validation.api.TravelCalculatePremiumRequestValidator;
import org.romilMasnaviev.travel.insurance.validation.validators.RequestValidation;
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
            validation.validate(request).ifPresent(errors::add);
            validation.validateList(request).ifPresent(errors::addAll);
        }
        return errors;
    }

}
