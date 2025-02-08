package org.javaguru.travel.insurance.service.validation;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.javaguru.travel.insurance.TestUtils.futureDate;

class AgreementDateFromValidationTest {

    AgreementDateFromValidation dateFromValidation = new AgreementDateFromValidation();

    @Test
    void whenAgreementDateFromIsNull_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .build();

        Optional<ValidationError> optionalValidationError = dateFromValidation.execute(request);

        assertThat(optionalValidationError.get().getField()).isEqualTo("agreementDateFrom");
        assertThat(optionalValidationError.get().getMessage()).isEqualTo("Must not be empty!");
    }

    @Test
    void whenAgreementDateToIsNormal_thenNoErrors() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateFrom(futureDate(2))
                .build();

        Optional<ValidationError> optionalValidationError = dateFromValidation.execute(request);

        assertThat(optionalValidationError.isEmpty()).isTrue();
    }

}