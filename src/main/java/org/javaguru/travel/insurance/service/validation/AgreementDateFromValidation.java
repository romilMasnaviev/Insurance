package org.javaguru.travel.insurance.service.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.javaguru.travel.insurance.service.ErrorConfig;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateFromValidation implements RequestValidation {

    private final ErrorConfig errorConfig;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null) ?
                Optional.of(errorConfig.buildError("ERROR_CODE_3")) :
                Optional.empty();
    }
}
