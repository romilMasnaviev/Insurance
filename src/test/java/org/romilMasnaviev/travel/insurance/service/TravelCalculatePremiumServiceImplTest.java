package org.romilMasnaviev.travel.insurance.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.RiskPremium;
import org.romilMasnaviev.travel.insurance.dto.response.ValidationError;
import org.romilMasnaviev.travel.insurance.underwriting.TravelCalculationResult;
import org.romilMasnaviev.travel.insurance.underwriting.TravelPremiumUnderwriting;
import org.romilMasnaviev.travel.insurance.validation.TravelCalculatePremiumRequestValidator;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.romilMasnaviev.travel.insurance.TestUtils.futureDate;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    TravelPremiumUnderwriting underwriting;

    @Mock
    TravelCalculatePremiumRequestValidator validator;

    @InjectMocks
    TravelCalculatePremiumServiceImpl service;


    @Test
    void calculatePremium_whenValidRequest_ThenReturnFullResponse() {
        var request = TravelCalculatePremiumRequest.builder()
                .personLastName("personLastName")
                .country("country")
                .agreementDateFrom(futureDate(1))
                .agreementDateTo(futureDate(2))
                .selectedRisks(List.of("selectedRisk"))
                .personFirstName("personFirstName")
                .personLastName("personLastName")
                .build();

        when(underwriting.calculatePremium(request)).thenReturn(
                new TravelCalculationResult(
                        new BigDecimal(110),
                        List.of(new RiskPremium())));

        when(validator.validate(any(TravelCalculatePremiumRequest.class))).thenReturn(List.of());

        var response = service.calculatePremium(request);

        assertEquals(new BigDecimal(110), response.getAgreementPremium());
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
        assertEquals(request.getCountry(), response.getCountry());
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }

    @Test
    void calculatePremium_whenInvalidRequest_ThenReturnResponseWithError() {
        var request = TravelCalculatePremiumRequest.builder()
                .build();

        when(validator.validate(any(TravelCalculatePremiumRequest.class)))
                .thenReturn(List.of(new ValidationError("errorCode", "description")));

        var response = service.calculatePremium(request);

        assertFalse(response.getErrors().isEmpty());
    }
}