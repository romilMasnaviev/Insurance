package org.javaguru.travel.insurance.validation.api;

import org.javaguru.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.response.ValidationError;

import java.util.List;

@FunctionalInterface
public interface TravelCalculatePremiumRequestValidator {
    List<ValidationError> validate(TravelCalculatePremiumRequest request);
}
