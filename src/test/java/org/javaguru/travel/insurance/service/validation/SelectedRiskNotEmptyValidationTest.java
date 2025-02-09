package org.javaguru.travel.insurance.service.validation;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.javaguru.travel.insurance.service.ErrorConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SelectedRiskNotEmptyValidationTest {

    @Mock
    ErrorConfig errorConfig;

    @InjectMocks
    private SelectedRiskNotEmptyValidation riskNotEmptyValidation;

    @Test
    void whenSelectedRiskIsNull_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder().build();

        when(errorConfig.buildError("ERROR_CODE_5")).thenReturn(new ValidationError("ERROR_CODE_5", "Field selectedRisks is Empty!"));
        Optional<ValidationError> optionalValidationError = riskNotEmptyValidation.execute(request);

        assertThat(optionalValidationError.get().getErrorCode()).isEqualTo("ERROR_CODE_5");
        assertThat(optionalValidationError.get().getDescription()).isEqualTo("Field selectedRisks is Empty!");
    }

    @Test
    void whenSelectedRiskIsEmpty_thenReturnError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder().selectedRisks(List.of("")).build();

        when(errorConfig.buildError("ERROR_CODE_5")).thenReturn(new ValidationError("ERROR_CODE_5", "Field selectedRisks is Empty!"));
        Optional<ValidationError> optionalValidationError = riskNotEmptyValidation.execute(request);

        assertThat(optionalValidationError.get().getErrorCode()).isEqualTo("ERROR_CODE_5");
        assertThat(optionalValidationError.get().getDescription()).isEqualTo("Field selectedRisks is Empty!");
    }

    @Test
    void whenSelectedRiskValid_thenNoErrors() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder().selectedRisks(List.of("not empty")).build();

        Optional<ValidationError> optionalValidationError = riskNotEmptyValidation.execute(request);

        assertThat(optionalValidationError.isEmpty()).isTrue();
    }


}