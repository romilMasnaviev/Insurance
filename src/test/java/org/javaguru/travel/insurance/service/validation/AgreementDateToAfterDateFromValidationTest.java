package org.javaguru.travel.insurance.service.validation;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.javaguru.travel.insurance.TestData.agreementDateTo;
import static org.javaguru.travel.insurance.TestData.mustBeAfterAAgreementDateFrom;
import static org.javaguru.travel.insurance.TestUtils.futureDate;

class AgreementDateToAfterDateFromValidationTest {

    AgreementDateToAfterDateFromValidation dateToAfterDateFromValidation = new AgreementDateToAfterDateFromValidation();

    @Test
    void whenDatesAreEqual_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateTo(futureDate(0))
                .agreementDateFrom(futureDate(0))
                .build();

        Optional<ValidationError> optionalValidationError = dateToAfterDateFromValidation.execute(request);

        assertThat(optionalValidationError.get().getMessage()).isEqualTo(mustBeAfterAAgreementDateFrom);
        assertThat(optionalValidationError.get().getField()).isEqualTo(agreementDateTo);
    }

    @Test
    void whenAgreementDateFromIsAfterAgreementDateTo_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateTo(futureDate(0))
                .agreementDateFrom(futureDate(1))
                .build();

        Optional<ValidationError> optionalValidationError = dateToAfterDateFromValidation.execute(request);

        assertThat(optionalValidationError.get().getMessage()).isEqualTo(mustBeAfterAAgreementDateFrom);
        assertThat(optionalValidationError.get().getField()).isEqualTo(agreementDateTo);
    }

    @Test
    void whenAgreementDateFromIsBeforeAgreementDateTo_thenNoErrors() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateTo(futureDate(1))
                .agreementDateFrom(futureDate(0))
                .build();

        Optional<ValidationError> optionalValidationError = dateToAfterDateFromValidation.execute(request);

        assertThat(optionalValidationError.isEmpty()).isTrue();
    }

}