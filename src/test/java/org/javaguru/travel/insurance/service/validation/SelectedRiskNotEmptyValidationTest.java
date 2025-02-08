package org.javaguru.travel.insurance.service.validation;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class SelectedRiskNotEmptyValidationTest {

    SelectedRiskNotEmptyValidation riskNotEmptyValidation = new SelectedRiskNotEmptyValidation();

    @Test
    void whenSelectedRiskIsNull_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder().build();

        Optional<ValidationError> optionalValidationError = riskNotEmptyValidation.execute(request);

        assertThat(optionalValidationError.get().getField()).isEqualTo("selectedRisks");
        assertThat(optionalValidationError.get().getMessage()).isEqualTo("Must not be empty!");
    }

    @Test
    void whenSelectedRiskIsEmpty_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder().selectedRisks(List.of("")).build();

        Optional<ValidationError> optionalValidationError = riskNotEmptyValidation.execute(request);

        assertThat(optionalValidationError.get().getField()).isEqualTo("selectedRisks");
        assertThat(optionalValidationError.get().getMessage()).isEqualTo("Must not be empty!");
    }

    @Test
    void whenSelectedRiskValid_thenNoErrors() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder().selectedRisks(List.of("not empty")).build();

        Optional<ValidationError> optionalValidationError = riskNotEmptyValidation.execute(request);

        assertThat(optionalValidationError.isEmpty()).isTrue();
    }


}