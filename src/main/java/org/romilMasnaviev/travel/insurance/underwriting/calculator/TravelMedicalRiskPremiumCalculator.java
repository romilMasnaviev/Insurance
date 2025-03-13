package org.romilMasnaviev.travel.insurance.underwriting.calculator;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.model.CountryDefaultDayRate;
import org.romilMasnaviev.travel.insurance.repository.CountryDefaultDayRateRepository;
import org.romilMasnaviev.travel.insurance.underwriting.TravelRiskPremiumCalculator;
import org.romilMasnaviev.travel.insurance.util.DateTimeUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {
    private final DateTimeUtil dateTimeUtil;
    private final CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        var countryCount = calculateDaysBetween(request);
        var countryDefaultRate = findCountryDefaultDayRate(request);
        return countryCount.multiply(countryDefaultRate).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal findCountryDefaultDayRate(TravelCalculatePremiumRequest request) {
        return countryDefaultDayRateRepository.findByCountryIc(request.getCountry()).map(CountryDefaultDayRate::getDefaultDayRate).orElseThrow(() -> new RuntimeException("Country day rate not found by country id = " + request.getCountry()));
    }

    private BigDecimal calculateDaysBetween(TravelCalculatePremiumRequest request) {
        return new BigDecimal(dateTimeUtil.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo()));
    }
}
