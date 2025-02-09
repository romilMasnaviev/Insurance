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
class AgreementDateToAfterDateFromValidationTest {

    @Mock
    private ErrorConfig errorConfig;

    @InjectMocks
    private AgreementDateToAfterDateFromValidation dateToAfterDateFromValidation;

    @Test
    void whenDatesAreEqual_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateTo(futureDate(0))
                .agreementDateFrom(futureDate(0))
                .build();

        when(errorConfig.buildError("ERROR_CODE_8")).thenReturn(new ValidationError("ERROR_CODE_8", "Field agreementDateTo before field AgreementDateFrom!"));
        Optional<ValidationError> optionalValidationError = dateToAfterDateFromValidation.execute(request);

        assertThat(optionalValidationError.get().getErrorCode()).isEqualTo("ERROR_CODE_8");
        assertThat(optionalValidationError.get().getDescription()).isEqualTo("Field agreementDateTo before field AgreementDateFrom!");
    }

    @Test
    void whenAgreementDateFromIsAfterAgreementDateTo_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateTo(futureDate(0))
                .agreementDateFrom(futureDate(1))
                .build();

        when(errorConfig.buildError("ERROR_CODE_8")).thenReturn(new ValidationError("ERROR_CODE_8", "Field agreementDateTo before field AgreementDateFrom!"));
        Optional<ValidationError> optionalValidationError = dateToAfterDateFromValidation.execute(request);

        assertThat(optionalValidationError.get().getErrorCode()).isEqualTo("ERROR_CODE_8");
        assertThat(optionalValidationError.get().getDescription()).isEqualTo("Field agreementDateTo before field AgreementDateFrom!");
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