package org.romilMasnaviev.travel.insurance.validation.validators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.ValidationError;
import org.romilMasnaviev.travel.insurance.validation.factory.ValidationErrorFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateToInFutureValidation implements RequestValidation {

    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateTo() == null) {
            return Optional.empty();
        }
        return (request.getAgreementDateTo().before(Date.from(Instant.now()))) ?
                Optional.of(validationErrorFactory.buildError("ERROR_CODE_7")) :
                Optional.empty();
    }
}
