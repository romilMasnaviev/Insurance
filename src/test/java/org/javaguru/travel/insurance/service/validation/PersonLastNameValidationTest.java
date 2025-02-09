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
import static org.javaguru.travel.insurance.TestData.normalLastName;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonLastNameValidationTest {

    @Mock
    private ErrorConfig errorConfig;

    @InjectMocks
    private PersonLastNameValidation lastNameValidation;

    @Test
    void whenLastNameIsEmpty_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .personLastName("")
                .build();

        when(errorConfig.buildError("ERROR_CODE_2")).thenReturn(new ValidationError("ERROR_CODE_2", "Field personLastName is empty!"));
        Optional<ValidationError> optionalValidationError = lastNameValidation.execute(request);

        assertThat(optionalValidationError.get().getErrorCode()).isEqualTo("ERROR_CODE_2");
        assertThat(optionalValidationError.get().getDescription()).isEqualTo("Field personLastName is empty!");
    }

    @Test
    void whenLastNameIsNull_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .build();

        when(errorConfig.buildError("ERROR_CODE_2")).thenReturn(new ValidationError("ERROR_CODE_2", "Field personLastName is empty!"));
        Optional<ValidationError> optionalValidationError = lastNameValidation.execute(request);

        assertThat(optionalValidationError.get().getErrorCode()).isEqualTo("ERROR_CODE_2");
        assertThat(optionalValidationError.get().getDescription()).isEqualTo("Field personLastName is empty!");
    }

    @Test
    void whenLastNameIsValid_thenNoErrors() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .personLastName(normalLastName)
                .build();

        Optional<ValidationError> optionalValidationError = lastNameValidation.execute(request);

        assertThat(optionalValidationError.isEmpty()).isTrue();
    }

}