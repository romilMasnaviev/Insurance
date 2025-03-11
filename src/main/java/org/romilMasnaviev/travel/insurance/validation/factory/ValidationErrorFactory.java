package org.romilMasnaviev.travel.insurance.validation.factory;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.romilMasnaviev.travel.insurance.dto.response.ValidationError;
import org.romilMasnaviev.travel.insurance.util.ErrorCodeUtil;
import org.romilMasnaviev.travel.insurance.util.PlaceHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ValidationErrorFactory {

    private final ErrorCodeUtil errorCodeUtil;

    public ValidationError buildError(String errorCode) {
        return new ValidationError(errorCode, errorCodeUtil.getErrorDescription(errorCode));
    }

    public ValidationError buildError(String errorCode, List<PlaceHolder> placeholders) {
        return new ValidationError(errorCode, errorCodeUtil.getErrorDescription(errorCode, placeholders));
    }

}
