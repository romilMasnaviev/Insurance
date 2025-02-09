package org.javaguru.travel.insurance.service.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.javaguru.travel.insurance.service.ErrorConfig;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateToAfterDateFromValidation implements RequestValidation {

    private final ErrorConfig errorConfig;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        Date to = request.getAgreementDateTo();
        Date from = request.getAgreementDateFrom();

        if (to == null || from == null) {
            return Optional.empty();
        }

        return !to.after(from) ?
                Optional.of(errorConfig.buildError("ERROR_CODE_8")) :
                Optional.empty();
    }

}