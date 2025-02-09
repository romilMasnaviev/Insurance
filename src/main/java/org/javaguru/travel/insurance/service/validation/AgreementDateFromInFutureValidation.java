package org.javaguru.travel.insurance.service.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.javaguru.travel.insurance.service.ErrorConfig;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateFromInFutureValidation implements RequestValidation {

    private final ErrorConfig errorConfig;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateFrom() == null) {
            return Optional.empty();
        }
        return (request.getAgreementDateFrom().before(Date.from(Instant.now()))) ?
                Optional.of(errorConfig.buildError("ERROR_CODE_6")) :
                Optional.empty();
    }
}
