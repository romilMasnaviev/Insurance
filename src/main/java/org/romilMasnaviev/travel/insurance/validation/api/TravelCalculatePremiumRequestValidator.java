package org.romilMasnaviev.travel.insurance.validation.api;

import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.ValidationError;

import java.util.List;

@FunctionalInterface
public interface TravelCalculatePremiumRequestValidator {
    List<ValidationError> validate(TravelCalculatePremiumRequest request);
}
