package org.javaguru.travel.insurance.service.validation;

import org.javaguru.travel.insurance.TestData;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.javaguru.travel.insurance.TestData.agreementDateTo;
import static org.javaguru.travel.insurance.TestUtils.futureDate;
import static org.javaguru.travel.insurance.TestUtils.pastDate;

class AgreementDateToInFutureValidationTest {

    AgreementDateToInFutureValidation dateToInFutureValidation = new AgreementDateToInFutureValidation();

    @Test
    void whenAgreementDateToInPast_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateTo(pastDate(1))
                .build();

        Optional<ValidationError> optionalValidationError = dateToInFutureValidation.execute(request);

        assertThat(optionalValidationError.get().getMessage()).isEqualTo(TestData.mustBeFuture);
        assertThat(optionalValidationError.get().getField()).isEqualTo(agreementDateTo);
    }

    @Test
    void whenAgreementDateToInPast_thenNoErrors() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateTo(futureDate(1))
                .build();

        Optional<ValidationError> optionalValidationError = dateToInFutureValidation.execute(request);

        assertThat(optionalValidationError.isEmpty()).isTrue();
    }

}