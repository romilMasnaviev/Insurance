package org.romilMasnaviev.travel.insurance.validation.validators;

import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.ValidationError;

import java.util.Optional;

public interface RequestValidation {

    Optional<ValidationError> execute(TravelCalculatePremiumRequest request);
}
