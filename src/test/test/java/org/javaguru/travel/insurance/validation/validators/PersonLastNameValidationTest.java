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
import static org.romilMasnaviev.travel.insurance.TestData.normalLastName;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonLastNameValidationTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private PersonLastNameValidation lastNameValidation;

    @Test
    void whenLastNameIsEmpty_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .personLastName("")
                .build();

        when(validationErrorFactory.buildError("ERROR_CODE_2")).thenReturn(new ValidationError("ERROR_CODE_2", "Field personLastName is empty!"));
        Optional<ValidationError> optionalValidationError = lastNameValidation.validate(request);

        assertTrue(optionalValidationError.isPresent());
        assertThat(optionalValidationError.get().getErrorCode()).isEqualTo("ERROR_CODE_2");
        assertThat(optionalValidationError.get().getDescription()).isEqualTo("Field personLastName is empty!");
    }

    @Test
    void whenLastNameIsNull_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .build();

        when(validationErrorFactory.buildError("ERROR_CODE_2")).thenReturn(new ValidationError("ERROR_CODE_2", "Field personLastName is empty!"));
        Optional<ValidationError> optionalValidationError = lastNameValidation.validate(request);

        assertTrue(optionalValidationError.isPresent());
        assertThat(optionalValidationError.get().getErrorCode()).isEqualTo("ERROR_CODE_2");
        assertThat(optionalValidationError.get().getDescription()).isEqualTo("Field personLastName is empty!");
    }

    @Test
    void whenLastNameIsValid_thenNoErrors() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .personLastName(normalLastName)
                .build();

        Optional<ValidationError> optionalValidationError = lastNameValidation.validate(request);

        assertThat(optionalValidationError.isEmpty()).isTrue();
    }

}