package org.romilMasnaviev.travel.insurance.service;

import lombok.RequiredArgsConstructor;
import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.TravelCalculatePremiumResponse;
import org.romilMasnaviev.travel.insurance.dto.response.ValidationError;
import org.romilMasnaviev.travel.insurance.underwriting.TravelCalculationResult;
import org.romilMasnaviev.travel.insurance.underwriting.TravelPremiumUnderwriting;
import org.romilMasnaviev.travel.insurance.validation.TravelCalculatePremiumRequestValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelPremiumUnderwriting underwriting;
    private final TravelCalculatePremiumRequestValidator validator;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = validator.validate(request);

        return errors.isEmpty() ?
                buildResponse(request, underwriting.calculatePremium(request)) :
                buildResponse(errors);
    }

    private TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

    private TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request,
                                                         TravelCalculationResult result) {
        return TravelCalculatePremiumResponse.builder()
                .agreementDateFrom(request.getAgreementDateFrom())
                .agreementDateTo(request.getAgreementDateTo())
                .personFirstName(request.getPersonFirstName())
                .personLastName(request.getPersonLastName())
                .agreementPremium(result.getTotalPremium())
                .risks(result.getRiskPremiums())
                .country(request.getCountry())
                .build();
    }

}
