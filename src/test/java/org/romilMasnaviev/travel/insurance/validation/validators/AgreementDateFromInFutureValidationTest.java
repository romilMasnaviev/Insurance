package org.romilMasnaviev.travel.insurance.validation.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.ValidationError;
import org.romilMasnaviev.travel.insurance.validation.factory.ValidationErrorFactory;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.romilMasnaviev.travel.insurance.TestUtils.futureDate;
import static org.romilMasnaviev.travel.insurance.TestUtils.pastDate;

@ExtendWith(MockitoExtension.class)
class AgreementDateFromInFutureValidationTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private AgreementDateFromInFutureValidation agreementDateFromInFutureValidation;

    @Test
    void whenAgreementDateFromInPast_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder().agreementDateFrom(pastDate(1)).build();

        when(validationErrorFactory.buildError("ERROR_CODE_6")).thenReturn(new ValidationError("ERROR_CODE_6", "Field agreementDateFrom in past tense!"));
        Optional<ValidationError> optionalValidationError = agreementDateFromInFutureValidation.validate(request);

        assertTrue(optionalValidationError.isPresent());
        assertThat(optionalValidationError.get().getErrorCode()).isEqualTo("ERROR_CODE_6");
        assertThat(optionalValidationError.get().getDescription()).isEqualTo("Field agreementDateFrom in past tense!");
    }

    @Test
    void whenAgreementDateFromInPast_thenNoErrors() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder().agreementDateFrom(futureDate(1)).build();

        Optional<ValidationError> optionalValidationError = agreementDateFromInFutureValidation.validate(request);

        assertThat(optionalValidationError.isEmpty()).isTrue();
    }

}