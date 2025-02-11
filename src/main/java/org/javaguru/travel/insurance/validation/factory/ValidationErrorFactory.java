package org.javaguru.travel.insurance.validation.factory;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.response.ValidationError;
import org.javaguru.travel.insurance.validation.config.ErrorConfig;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ValidationErrorFactory {

    private final ErrorConfig errorConfig;

    public ValidationError buildError(String errorCode) {
        return new ValidationError(errorCode, errorConfig.getErrorDescription(errorCode));
    }
}
