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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgreementDateFromValidationTest {

    @Mock
    private ErrorConfig errorConfig;

    @InjectMocks
    private AgreementDateFromValidation dateFromValidation;

    @Test
    void whenAgreementDateFromIsNull_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .build();

        when(errorConfig.buildError("ERROR_CODE_3")).thenReturn(new ValidationError("ERROR_CODE_3", "Field agreementDateFrom is empty!"));
        Optional<ValidationError> optionalValidationError = dateFromValidation.execute(request);

        assertThat(optionalValidationError.get().getErrorCode()).isEqualTo("ERROR_CODE_3");
        assertThat(optionalValidationError.get().getDescription()).isEqualTo("Field agreementDateFrom is empty!");
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