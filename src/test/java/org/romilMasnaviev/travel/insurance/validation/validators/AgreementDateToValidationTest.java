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
import static org.romilMasnaviev.travel.insurance.TestUtils.futureDate;

@ExtendWith(MockitoExtension.class)
class AgreementDateToValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private AgreementDateToValidation validation;

    @Test
    public void validate_WhenDateToIsNull_ShouldReturnValidationError() {
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
    public void validate_WhenDateToIsNotNull_ShouldReturnEmptyOptional() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateTo(futureDate(1))
                .build();

        Optional<ValidationError> errorOptional = validation.validate(request);

        Assertions.assertTrue(errorOptional.isEmpty());
    }

}