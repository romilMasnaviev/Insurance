package org.javaguru.travel.insurance.service.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class SelectedRiskNotEmptyValidation implements RequestValidation {
    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        if (request.getSelectedRisks() == null || request.getSelectedRisks().stream().anyMatch(String::isEmpty)) {
            return Optional.of(new ValidationError("selectedRisks", "Must not be empty!"));
        }
        return Optional.empty();
    }
}
