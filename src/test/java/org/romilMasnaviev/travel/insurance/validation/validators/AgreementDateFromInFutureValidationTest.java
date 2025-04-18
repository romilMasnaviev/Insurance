package org.romilMasnaviev.travel.insurance.validation.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.romilMasnaviev.travel.insurance.TestUtils;
import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.ValidationError;
import org.romilMasnaviev.travel.insurance.validation.factory.ValidationErrorFactory;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AgreementDateFromInFutureValidationTest {

    @Mock
    private ValidationErrorFactory factory;

    @InjectMocks
    private AgreementDateFromInFutureValidation validation;

    @Test
    public void validate_WhenDateFromIsNull_ShouldReturnEmptyOptional() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .build();
        Optional<ValidationError> errorOptional = validation.validate(request);
        Assertions.assertTrue(errorOptional.isEmpty());
    }

    @Test
    public void validate_WhenDateFromBeforeNow_ShouldReturnValidationError() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateFrom(TestUtils.pastDate(1))
                .build();

        Mockito.when(factory.buildError(Mockito.startsWith("ERROR_CODE")))
                .thenReturn(new ValidationError("errorCode", "description"));

        Optional<ValidationError> errorOptional = validation.validate(request);
        Assertions.assertTrue(errorOptional.isPresent());
    }
}