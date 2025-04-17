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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CountryValidationTest {

    @Mock
    ValidationErrorFactory errorFactory;

    @InjectMocks
    CountryValidation validation;

    @Test
    public void validate_WhenCountryIsNull_ShouldReturnValidationError() {
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
    public void validate_WhenCountryNotNull_ShouldReturnEmptyOptional() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .country("country")
                .build();

        Optional<ValidationError> errorOptional = validation.validate(request);

        Assertions.assertTrue(errorOptional.isEmpty());
    }

}