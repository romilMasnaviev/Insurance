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
class AgreementDateToValidationTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private AgreementDateToValidation dateToValidation;

    @Test
    void whenAgreementDateToIsNull_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .build();

        when(validationErrorFactory.buildError("ERROR_CODE_4")).thenReturn(new ValidationError("ERROR_CODE_4", "Field agreementDateTo is Empty!"));
        Optional<ValidationError> optionalValidationError = dateToValidation.validate(request);

        assertTrue(optionalValidationError.isPresent());
        assertThat(optionalValidationError.get().getErrorCode()).isEqualTo("ERROR_CODE_4");
        assertThat(optionalValidationError.get().getDescription()).isEqualTo("Field agreementDateTo is Empty!");

    }

    @Test
    void whenAgreementDateToIsNormal_thenNoErrors() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateTo(futureDate(1))
                .build();

        Optional<ValidationError> optionalValidationError = dateToValidation.validate(request);

        assertThat(optionalValidationError.isEmpty()).isTrue();
    }

}