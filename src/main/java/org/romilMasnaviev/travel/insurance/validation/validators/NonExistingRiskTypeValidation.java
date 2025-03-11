package org.romilMasnaviev.travel.insurance.validation.validators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.romilMasnaviev.travel.insurance.dto.response.ValidationError;
import org.romilMasnaviev.travel.insurance.repository.ClassifierValueRepository;
import org.romilMasnaviev.travel.insurance.util.PlaceHolder;
import org.romilMasnaviev.travel.insurance.validation.factory.ValidationErrorFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class NonExistingRiskTypeValidation extends TravelRequestValidationImpl {

    private final ClassifierValueRepository classifierValueRepository;
    private final ValidationErrorFactory errorFactory;

    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks() == null ?
                List.of() :
                validateSelectedRisks(request);
    }

    private List<ValidationError> validateSelectedRisks(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks().stream()
                .map(this::validateRiskIc)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<ValidationError> validateRiskIc(String riskIc) {
        return existInDatabase(riskIc) ? Optional.empty() : Optional.of(buildValidationError(riskIc));
    }

    private ValidationError buildValidationError(String riskIc) {
        var placeHolder = new PlaceHolder("NOT_EXISTING_RISK_TYPE", riskIc);
        return errorFactory.buildError("ERROR_CODE_9", List.of(placeHolder));
    }

    private boolean existInDatabase(String riskIc) {
        return classifierValueRepository.findByClassifierAndIc("RISK_TYPE", riskIc).isPresent();
    }
}
