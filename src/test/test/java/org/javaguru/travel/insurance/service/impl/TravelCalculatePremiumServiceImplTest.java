package org.romilMasnaviev.travel.insurance.service.impl;

import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.TravelCalculatePremiumResponse;
import org.romilMasnaviev.travel.insurance.validation.api.TravelCalculatePremiumRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.romilMasnaviev.travel.insurance.TestData.normalFirstName;
import static org.romilMasnaviev.travel.insurance.TestData.normalLastName;
import static org.romilMasnaviev.travel.insurance.TestUtils.futureDate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    private TravelPremiumUnderwritingImpl underwriting;
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;
    @Mock
    private TravelCalculatePremiumRequestValidator validator;

    private TravelCalculatePremiumRequest request;

    @Test
    void testCalculatePremium_WhenDateFromIsSet_ThenReturnSameDateFrom() {
        request = TravelCalculatePremiumRequest.builder()
                .agreementDateFrom(futureDate(1))
                .build();

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertThat(response.getAgreementDateFrom()).isEqualTo(futureDate(1));
    }

    @Test
    void testCalculatePremium_WhenDateToIsSet_ThenReturnSameDateTo() {
        request = TravelCalculatePremiumRequest.builder()
                .agreementDateTo(futureDate(1))
                .build();

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertThat(response.getAgreementDateTo()).isEqualTo(futureDate(1));
    }

    @Test
    void testCalculatePremium_WhenFirstNameIsSet_ThenReturnSameFirstName() {
        request = TravelCalculatePremiumRequest.builder()
                .personFirstName(normalFirstName)
                .build();

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertThat(response.getPersonFirstName()).isEqualTo(normalFirstName);
    }

    @Test
    void testCalculatePremium_WhenLastNameIsSet_ThenReturnSameLastName() {
        request = TravelCalculatePremiumRequest.builder()
                .personLastName(normalLastName)
                .build();

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertThat(response.getPersonLastName()).isEqualTo(normalLastName);
    }

    @Test
    void testCalculatePremium_WhenAllFieldsAreSet_ThenReturnCorrectResponse() {
        request = TravelCalculatePremiumRequest.builder()
                .personFirstName(normalFirstName)
                .personLastName(normalLastName)
                .agreementDateFrom(futureDate(1))
                .agreementDateTo(futureDate(2))
                .build();

        when(underwriting.calculateAgreementPrice(any())).thenReturn(new BigDecimal(9L));

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertThat(response.getAgreementDateFrom()).isEqualTo(futureDate(1));
        assertThat(response.getAgreementDateTo()).isEqualTo(futureDate(2));
        assertThat(response.getPersonFirstName()).isEqualTo(normalFirstName);
        assertThat(response.getPersonLastName()).isEqualTo(normalLastName);
        assertThat(response.getAgreementPrice()).isEqualTo(BigDecimal.valueOf(9L));
    }

    @Test
    void testCalculatePremium_WhenDatesAreEqual_ThenPriceIsZero() {
        request = TravelCalculatePremiumRequest.builder()
                .agreementDateFrom(futureDate(1))
                .agreementDateTo(futureDate(1))
                .build();

        when(underwriting.calculateAgreementPrice(any())).thenReturn(new BigDecimal(0L));

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertThat(response.getAgreementPrice()).isEqualTo(BigDecimal.valueOf(0L));
    }

    @Test
    void testCalculatePremium_WhenDatesAreDifferent_ThenCalculateCorrectPrice() {
        request = TravelCalculatePremiumRequest.builder()
                .agreementDateFrom(futureDate(1))
                .agreementDateTo(futureDate(20))
                .build();

        when(underwriting.calculateAgreementPrice(any())).thenReturn(new BigDecimal(19L));

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertThat(response.getAgreementPrice()).isEqualTo(BigDecimal.valueOf(19L));
    }
}