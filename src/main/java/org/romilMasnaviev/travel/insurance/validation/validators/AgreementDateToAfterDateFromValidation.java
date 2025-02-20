package org.romilMasnaviev.travel.insurance.validation.validators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.ValidationError;
import org.romilMasnaviev.travel.insurance.validation.factory.ValidationErrorFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateToAfterDateFromValidation extends TravelRequestValidationImpl {

    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date to = request.getAgreementDateTo();
        Date from = request.getAgreementDateFrom();

        if (to == null || from == null) {
            return Optional.empty();
        }

        return !to.after(from) ?
                Optional.of(validationErrorFactory.buildError("ERROR_CODE_8")) :
                Optional.empty();
    }

}
