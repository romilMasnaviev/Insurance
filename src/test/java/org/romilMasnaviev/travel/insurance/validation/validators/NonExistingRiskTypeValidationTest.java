package org.romilMasnaviev.travel.insurance.validation.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.ValidationError;
import org.romilMasnaviev.travel.insurance.model.ClassifierValue;
import org.romilMasnaviev.travel.insurance.repository.ClassifierValueRepository;
import org.romilMasnaviev.travel.insurance.validation.factory.ValidationErrorFactory;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NonExistingRiskTypeValidationTest {

    @Mock
    ClassifierValueRepository repository;

    @Mock
    ValidationErrorFactory errorFactory;

    @InjectMocks
    NonExistingRiskTypeValidation validation;

    @Test
    public void validate_WhenRisksIsNull_ShouldReturnEmptyList() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .build();

        List<ValidationError> validationErrors = validation.validateList(request);

        assertTrue(validationErrors.isEmpty());
    }

    @Test
    public void validate_WhenRisksIsNotNullAndContainsInRepository_ShouldReturnEmptyList() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .selectedRisks(List.of("TRAVEL_MEDICAL"))
                .build();

        when(repository.findByClassifierAndIc("RISK_TYPE", "TRAVEL_MEDICAL"))
                .thenReturn(Optional.of(new ClassifierValue()));

        List<ValidationError> validationErrors = validation.validateList(request);

        assertTrue(validationErrors.isEmpty());
    }

    @Test
    public void validate_WhenRisksIsNotNullAndDoesntContainsInRepository_ShouldReturnEmptyList() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .selectedRisks(List.of("TRAVEL_MEDICAL"))
                .build();

        when(repository.findByClassifierAndIc("RISK_TYPE", "TRAVEL_MEDICAL"))
                .thenReturn(Optional.empty());

        when(errorFactory.buildError(Mockito.startsWith("ERROR_CODE"), Mockito.anyList()))
                .thenReturn(new ValidationError("errorCode", "description"));

        List<ValidationError> validationErrors = validation.validateList(request);

        assertFalse(validationErrors.isEmpty());
        assertEquals("errorCode", validationErrors.get(0).errorCode());
        assertEquals("description", validationErrors.get(0).description());
    }


}