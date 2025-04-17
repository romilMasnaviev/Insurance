package org.romilMasnaviev.travel.insurance.validation.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.ValidationError;
import org.romilMasnaviev.travel.insurance.validation.factory.ValidationErrorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.when;
import static org.romilMasnaviev.travel.insurance.TestUtils.futureDate;
import static org.romilMasnaviev.travel.insurance.TestUtils.pastDate;

@ExtendWith(MockitoExtension.class)
class AgreementDateToInFutureValidationTest {

    @Mock
    ValidationErrorFactory errorFactory;

    @InjectMocks
    AgreementDateToInFutureValidation validation;

    @Test
    public void validate_WhenDateToIsNull_ShouldReturnEmptyOptional() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .build();

        var errorOptional = validation.validate(request);

        assertTrue(errorOptional.isEmpty());
    }

    @Test
    public void validate_WhenDateToInPast_ShouldReturnValidationError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateTo(pastDate(1))
                .build();

        when(errorFactory.buildError(startsWith("ERROR_CODE")))
                .thenReturn(new ValidationError("errorCode", "description"));

        var errorOptional = validation.validate(request);

        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().errorCode(), "errorCode");
        assertEquals(errorOptional.get().description(), "description");
    }

    @Test
    public void validate_WhenDateToInFuture_ShouldReturnEmptyOptional() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateTo(futureDate(1))
                .build();

        var errorOptional = validation.validate(request);

        assertTrue(errorOptional.isEmpty());
    }
}