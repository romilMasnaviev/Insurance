package org.romilMasnaviev.travel.insurance.validation.validators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.ValidationError;
import org.romilMasnaviev.travel.insurance.validation.factory.ValidationErrorFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class SelectedRiskNotEmptyValidation extends TravelRequestValidationImpl {

    private final ValidationErrorFactory validationErrorFactory;

    //TODO
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        if (request.getSelectedRisks() == null || request.getSelectedRisks().stream().anyMatch(String::isEmpty)) {
            return Optional.of(validationErrorFactory.buildError("ERROR_CODE_5"));
        }
        return Optional.empty();
    }
}
