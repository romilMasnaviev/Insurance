package org.romilMasnaviev.travel.insurance.validation.factory;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.romilMasnaviev.travel.insurance.dto.response.ValidationError;
import org.romilMasnaviev.travel.insurance.validation.config.ErrorConfig;
import org.romilMasnaviev.travel.insurance.validation.config.PlaceHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ValidationErrorFactory {

    private final ErrorConfig errorConfig;

    public ValidationError buildError(String errorCode) {
        return new ValidationError(errorCode, errorConfig.getErrorDescription(errorCode));
    }

    public ValidationError buildError(String errorCode, List<PlaceHolder> placeholders) {
        return new ValidationError(errorCode, errorConfig.getErrorDescription(errorCode, placeholders));
    }

}
