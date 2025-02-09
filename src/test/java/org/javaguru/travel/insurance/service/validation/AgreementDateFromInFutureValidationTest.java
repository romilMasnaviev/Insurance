package org.javaguru.travel.insurance.service.validation;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.javaguru.travel.insurance.service.ErrorConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.javaguru.travel.insurance.TestUtils.futureDate;
import static org.javaguru.travel.insurance.TestUtils.pastDate;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgreementDateFromInFutureValidationTest {

    @Mock
    private ErrorConfig errorConfig;
    @InjectMocks
    private AgreementDateFromInFutureValidation agreementDateFromInFutureValidation;

    @Test
    void whenAgreementDateFromInPast_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder().agreementDateFrom(pastDate(1)).build();

        when(errorConfig.buildError("ERROR_CODE_6")).thenReturn(new ValidationError("ERROR_CODE_6", "Field agreementDateFrom in past tense!"));
        Optional<ValidationError> optionalValidationError = agreementDateFromInFutureValidation.execute(request);

        assertThat(optionalValidationError.get().getErrorCode()).isEqualTo("ERROR_CODE_6");
        assertThat(optionalValidationError.get().getDescription()).isEqualTo("Field agreementDateFrom in past tense!");
    }

    @Test
    void whenAgreementDateFromInPast_thenNoErrors() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder().agreementDateFrom(futureDate(1)).build();

        Optional<ValidationError> optionalValidationError = agreementDateFromInFutureValidation.execute(request);

        assertThat(optionalValidationError.isEmpty()).isTrue();
    }

}