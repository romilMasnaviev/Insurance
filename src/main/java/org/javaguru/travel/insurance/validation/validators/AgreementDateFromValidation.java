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
class AgreementDateFromValidation implements RequestValidation {

    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null) ?
                Optional.of(validationErrorFactory.buildError("ERROR_CODE_3")) :
                Optional.empty();
    }
}
