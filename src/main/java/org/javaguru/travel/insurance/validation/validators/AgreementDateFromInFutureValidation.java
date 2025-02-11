package org.javaguru.travel.insurance.validation.validators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.response.ValidationError;
import org.javaguru.travel.insurance.validation.factory.ValidationErrorFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateFromInFutureValidation implements RequestValidation {

    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateFrom() == null) {
            return Optional.empty();
        }
        return (request.getAgreementDateFrom().before(Date.from(Instant.now()))) ?
                Optional.of(validationErrorFactory.buildError("ERROR_CODE_6")) :
                Optional.empty();
    }
}
