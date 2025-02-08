package org.javaguru.travel.insurance.service.validation;

import org.javaguru.travel.insurance.TestData;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.javaguru.travel.insurance.TestData.agreementDateFrom;
import static org.javaguru.travel.insurance.TestUtils.futureDate;
import static org.javaguru.travel.insurance.TestUtils.pastDate;

class AgreementDateFromInFutureValidationTest {

    AgreementDateFromInFutureValidation agreementDateFromInFutureValidation = new AgreementDateFromInFutureValidation();

    @Test
    void whenAgreementDateFromInPast_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateFrom(pastDate(1))
                .build();

        Optional<ValidationError> optionalValidationError = agreementDateFromInFutureValidation.execute(request);

        assertThat(optionalValidationError.get().getMessage()).isEqualTo(TestData.mustBeFuture);
        assertThat(optionalValidationError.get().getField()).isEqualTo(agreementDateFrom);
    }

    @Test
    void whenAgreementDateFromInPast_thenNoErrors() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateFrom(futureDate(1))
                .build();

        Optional<ValidationError> optionalValidationError = agreementDateFromInFutureValidation.execute(request);

        assertThat(optionalValidationError.isEmpty()).isTrue();
    }

}