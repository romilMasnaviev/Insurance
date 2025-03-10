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
import static org.romilMasnaviev.travel.insurance.TestData.normalFirstName;

@ExtendWith(MockitoExtension.class)
class PersonFirstNameValidationTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private PersonFirstNameValidation firstNameValidation;

    @Test
    void whenFirstNameIsEmpty_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .personFirstName("")
                .build();

        when(validationErrorFactory.buildError("ERROR_CODE_1")).thenReturn(new ValidationError("ERROR_CODE_1", "Field personFirstName is empty!"));
        Optional<ValidationError> optionalValidationError = firstNameValidation.validate(request);

        assertTrue(optionalValidationError.isPresent());
        assertThat(optionalValidationError.get().getErrorCode()).isEqualTo("ERROR_CODE_1");
        assertThat(optionalValidationError.get().getDescription()).isEqualTo("Field personFirstName is empty!");
    }

    @Test
    void whenFirstNameIsNull_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .build();

        when(validationErrorFactory.buildError("ERROR_CODE_1")).thenReturn(new ValidationError("ERROR_CODE_1", "Field personFirstName is empty!"));
        Optional<ValidationError> optionalValidationError = firstNameValidation.validate(request);

        assertTrue(optionalValidationError.isPresent());
        assertThat(optionalValidationError.get().getErrorCode()).isEqualTo("ERROR_CODE_1");
        assertThat(optionalValidationError.get().getDescription()).isEqualTo("Field personFirstName is empty!");
    }

    @Test
    void whenFirstNameIsValid_thenNoErrors() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .personFirstName(normalFirstName)
                .build();

        Optional<ValidationError> optionalValidationError = firstNameValidation.validate(request);

        assertThat(optionalValidationError.isEmpty()).isTrue();
    }

}