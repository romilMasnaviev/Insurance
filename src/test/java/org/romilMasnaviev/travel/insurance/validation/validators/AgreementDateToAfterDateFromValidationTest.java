package org.romilMasnaviev.travel.insurance.validation.validators;

import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.ValidationError;
import org.romilMasnaviev.travel.insurance.validation.factory.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.romilMasnaviev.travel.insurance.TestUtils.futureDate;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgreementDateToAfterDateFromValidationTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private AgreementDateToAfterDateFromValidation dateToAfterDateFromValidation;

    @Test
    void whenDatesAreEqual_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateTo(futureDate(0))
                .agreementDateFrom(futureDate(0))
                .build();

        when(validationErrorFactory.buildError("ERROR_CODE_8")).thenReturn(new ValidationError("ERROR_CODE_8", "Field agreementDateTo before field AgreementDateFrom!"));
        Optional<ValidationError> optionalValidationError = dateToAfterDateFromValidation.validate(request);

        assertTrue(optionalValidationError.isPresent());
        assertThat(optionalValidationError.get().getErrorCode()).isEqualTo("ERROR_CODE_8");
        assertThat(optionalValidationError.get().getDescription()).isEqualTo("Field agreementDateTo before field AgreementDateFrom!");
    }

    @Test
    void whenAgreementDateFromIsAfterAgreementDateTo_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateTo(futureDate(0))
                .agreementDateFrom(futureDate(1))
                .build();

        when(validationErrorFactory.buildError("ERROR_CODE_8")).thenReturn(new ValidationError("ERROR_CODE_8", "Field agreementDateTo before field AgreementDateFrom!"));
        Optional<ValidationError> optionalValidationError = dateToAfterDateFromValidation.validate(request);

        assertTrue(optionalValidationError.isPresent());
        assertThat(optionalValidationError.get().getErrorCode()).isEqualTo("ERROR_CODE_8");
        assertThat(optionalValidationError.get().getDescription()).isEqualTo("Field agreementDateTo before field AgreementDateFrom!");
    }

    @Test
    void whenAgreementDateFromIsBeforeAgreementDateTo_thenNoErrors() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateTo(futureDate(1))
                .agreementDateFrom(futureDate(0))
                .build();

        Optional<ValidationError> optionalValidationError = dateToAfterDateFromValidation.validate(request);

        assertThat(optionalValidationError.isEmpty()).isTrue();
    }

}