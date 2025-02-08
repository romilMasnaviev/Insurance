package org.javaguru.travel.insurance.service.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.javaguru.travel.insurance.service.TravelCalculatePremiumRequestValidator;
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
