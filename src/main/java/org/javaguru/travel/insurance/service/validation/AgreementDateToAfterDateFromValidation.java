package org.javaguru.travel.insurance.service.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateToAfterDateFromValidation implements RequestValidation {

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        Date to = request.getAgreementDateTo();
        Date from = request.getAgreementDateFrom();

        if (to == null || from == null) {
            return Optional.empty();
        }

        return !to.after(from) ?
                Optional.of(new ValidationError("AgreementDateTo",
                        "must be after AgreementDateFrom")) :
                Optional.empty();
    }

}
