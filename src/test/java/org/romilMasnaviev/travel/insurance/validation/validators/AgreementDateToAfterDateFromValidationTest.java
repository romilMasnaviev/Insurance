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

@ExtendWith(MockitoExtension.class)
class AgreementDateToAfterDateFromValidationTest {

    @Mock
    ValidationErrorFactory errorFactory;

    @InjectMocks
    AgreementDateToAfterDateFromValidation validation;

    @Test
    public void validate_WhenDateFromAndDateToIsNull_ShouldReturnValidationError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateFrom(null)
                .agreementDateTo(null)
                .build();

        var errorOptional = validation.validate(request);

        assertTrue(errorOptional.isEmpty());
    }

    @Test
    public void validate_WhenDateFromAfterDateTo_ShouldReturnValidationError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateFrom(futureDate(2))
                .agreementDateTo(futureDate(1))
                .build();

        when(errorFactory.buildError(startsWith("ERROR_CODE")))
                .thenReturn(new ValidationError("errorCode", "description"));

        var errorOptional = validation.validate(request);

        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().errorCode(), "errorCode");
        assertEquals(errorOptional.get().description(), "description");
    }

    @Test
    public void validate_WhenDateToAfterDateFrom_ShouldReturnEmptyOptional() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateFrom(futureDate(1))
                .agreementDateTo(futureDate(2))
                .build();

        var errorOptional = validation.validate(request);

        assertTrue(errorOptional.isEmpty());
    }
}