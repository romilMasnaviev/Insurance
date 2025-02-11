package org.javaguru.travel.insurance.validation.validators;

import org.javaguru.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.response.ValidationError;

import java.util.Optional;

public interface RequestValidation {

    Optional<ValidationError> execute(TravelCalculatePremiumRequest request);
}
