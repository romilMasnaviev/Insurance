package org.romilMasnaviev.travel.insurance.validation.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.List;

@Configuration
@PropertySource("classpath:errorCodes.properties")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ErrorConfig {

    private final Environment environment;

    public String getErrorDescription(String errorCode) {
        String description = environment.getProperty(errorCode);
        return description != null ? description : "Unknown error";
    }

    public String getErrorDescription(String errorCode, List<PlaceHolder> placeholders) {
        String description = environment.getProperty(errorCode);
        for (PlaceHolder placeholder : placeholders) {
            String placeholderToReplace = "{" + placeholder.getPlaceholderName() + "}";
            description = description.replace(placeholderToReplace, placeholder.getPlaceholderValue());
        }
        return description;
    }

}
