package org.javaguru.travel.insurance.service.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateToInFutureValidation implements RequestValidation {

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateTo() == null) {
            return Optional.empty();
        }
        return (request.getAgreementDateTo().before(Date.from(Instant.now()))) ?
                Optional.of(new ValidationError("AgreementDateTo", "Must be in future!")) :
                Optional.empty();
    }
}
