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
class AgreementDateToInFutureValidationTest {

    @Mock
    private ErrorConfig errorConfig;

    @InjectMocks
    private AgreementDateToInFutureValidation dateToInFutureValidation;


    @Test
    void whenAgreementDateToInPast_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateTo(pastDate(1))
                .build();

        when(errorConfig.buildError("ERROR_CODE_7")).thenReturn(new ValidationError("ERROR_CODE_7", "Field agreementDateTo in past tense!"));
        Optional<ValidationError> optionalValidationError = dateToInFutureValidation.execute(request);

        assertThat(optionalValidationError.get().getErrorCode()).isEqualTo("ERROR_CODE_7");
        assertThat(optionalValidationError.get().getDescription()).isEqualTo("Field agreementDateTo in past tense!");
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