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
import static org.javaguru.travel.insurance.TestData.normalFirstName;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonFirstNameValidationTest {

    @Mock
    private ErrorConfig errorConfig;

    @InjectMocks
    private PersonFirstNameValidation firstNameValidation;

    @Test
    void whenFirstNameIsEmpty_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .personFirstName("")
                .build();

        when(errorConfig.buildError("ERROR_CODE_1")).thenReturn(new ValidationError("ERROR_CODE_1", "Field personFirstName is empty!"));
        Optional<ValidationError> optionalValidationError = firstNameValidation.execute(request);

        assertThat(optionalValidationError.get().getErrorCode()).isEqualTo("ERROR_CODE_1");
        assertThat(optionalValidationError.get().getDescription()).isEqualTo("Field personFirstName is empty!");
    }

    @Test
    void whenFirstNameIsNull_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .build();

        when(errorConfig.buildError("ERROR_CODE_1")).thenReturn(new ValidationError("ERROR_CODE_1", "Field personFirstName is empty!"));
        Optional<ValidationError> optionalValidationError = firstNameValidation.execute(request);

        assertThat(optionalValidationError.get().getErrorCode()).isEqualTo("ERROR_CODE_1");
        assertThat(optionalValidationError.get().getDescription()).isEqualTo("Field personFirstName is empty!");
    }

    @Test
    void whenFirstNameIsValid_thenNoErrors() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .personFirstName(normalFirstName)
                .build();

        Optional<ValidationError> optionalValidationError = firstNameValidation.execute(request);

        assertThat(optionalValidationError.isEmpty()).isTrue();
    }

}