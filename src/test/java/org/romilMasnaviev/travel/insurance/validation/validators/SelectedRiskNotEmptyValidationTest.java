package org.romilMasnaviev.travel.insurance.validation.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.ValidationError;
import org.romilMasnaviev.travel.insurance.validation.factory.ValidationErrorFactory;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class SelectedRiskNotEmptyValidationTest {
    @Mock
    ValidationErrorFactory errorFactory;

    @InjectMocks
    SelectedRiskNotEmptyValidation validation;

    @Test
    public void validate_WhenSelectedRiskIsNull_ShouldReturnValidationError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .build();

        Mockito.when(errorFactory
                        .buildError(Mockito.startsWith("ERROR_CODE")))
                .thenReturn(new ValidationError("errorCode", "description"));
        Optional<ValidationError> errorOptional = validation.validate(request);

        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().description(), "description");
        assertEquals(errorOptional.get().errorCode(), "errorCode");
    }

    @Test
    public void validate_WhenSelectedRiskIsEmpty_ShouldReturnValidationError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .selectedRisks(List.of(""))
                .build();

        Mockito.when(errorFactory
                        .buildError(Mockito.startsWith("ERROR_CODE")))
                .thenReturn(new ValidationError("errorCode", "description"));
        Optional<ValidationError> errorOptional = validation.validate(request);

        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().description(), "description");
        assertEquals(errorOptional.get().errorCode(), "errorCode");
    }

    @Test
    public void validate_WhenValidSelectedRisk_ShouldReturnEmptyOptional() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .selectedRisks(List.of("selectedRisk"))
                .build();

        Optional<ValidationError> errorOptional = validation.validate(request);

        Assertions.assertTrue(errorOptional.isEmpty());
    }
}