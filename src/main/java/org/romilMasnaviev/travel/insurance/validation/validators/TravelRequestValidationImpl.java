package org.romilMasnaviev.travel.insurance.validation.validators;

import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.ValidationError;

import java.util.List;
import java.util.Optional;

abstract class TravelRequestValidationImpl implements RequestValidation {
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<List<ValidationError>> validateList(TravelCalculatePremiumRequest request) {
        return Optional.empty();
    }
}
