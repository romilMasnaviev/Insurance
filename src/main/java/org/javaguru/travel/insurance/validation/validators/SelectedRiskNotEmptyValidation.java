package org.javaguru.travel.insurance.validation.validators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.response.ValidationError;
import org.javaguru.travel.insurance.validation.factory.ValidationErrorFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class SelectedRiskNotEmptyValidation implements RequestValidation {

    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        if (request.getSelectedRisks() == null || request.getSelectedRisks().stream().anyMatch(String::isEmpty)) {
            return Optional.of(validationErrorFactory.buildError("ERROR_CODE_5"));
        }
        return Optional.empty();
    }
}
