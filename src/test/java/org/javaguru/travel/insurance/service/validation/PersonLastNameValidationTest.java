package org.javaguru.travel.insurance.service.validation;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.javaguru.travel.insurance.TestData.normalLastName;

class PersonLastNameValidationTest {

    PersonLastNameValidation lastNameValidation = new PersonLastNameValidation();

    @Test
    void whenLastNameIsEmpty_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .personLastName("")
                .build();

        Optional<ValidationError> optionalValidationError = lastNameValidation.execute(request);

        assertThat(optionalValidationError.get().getField()).isEqualTo("personLastName");
        assertThat(optionalValidationError.get().getMessage()).isEqualTo("Must not be empty!");
    }

    @Test
    void whenLastNameIsNull_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .build();

        Optional<ValidationError> optionalValidationError = lastNameValidation.execute(request);

        assertThat(optionalValidationError.get().getField()).isEqualTo("personLastName");
        assertThat(optionalValidationError.get().getMessage()).isEqualTo("Must not be empty!");
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