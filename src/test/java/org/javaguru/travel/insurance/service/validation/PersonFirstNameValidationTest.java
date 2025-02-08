package org.javaguru.travel.insurance.service.validation;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.javaguru.travel.insurance.TestData.normalFirstName;

class PersonFirstNameValidationTest {

    PersonFirstNameValidation firstNameValidation = new PersonFirstNameValidation();

    @Test
    void whenFirstNameIsEmpty_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .personFirstName("")
                .build();

        Optional<ValidationError> optionalValidationError = firstNameValidation.execute(request);

        assertThat(optionalValidationError.get().getField()).isEqualTo("personFirstName");
        assertThat(optionalValidationError.get().getMessage()).isEqualTo("Must not be empty!");
    }

    @Test
    void whenFirstNameIsNull_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .build();

        Optional<ValidationError> optionalValidationError = firstNameValidation.execute(request);

        assertThat(optionalValidationError.get().getField()).isEqualTo("personFirstName");
        assertThat(optionalValidationError.get().getMessage()).isEqualTo("Must not be empty!");
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