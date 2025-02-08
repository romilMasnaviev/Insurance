package org.javaguru.travel.insurance.service.validation;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;

import java.util.Optional;

public interface RequestValidation {

    Optional<ValidationError> execute(TravelCalculatePremiumRequest request);
}
