package org.javaguru.travel.insurance.service;

import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:errorCodes.properties")
public class ErrorConfig {
    private final Environment environment;

    public ErrorConfig(Environment environment) {
        this.environment = environment;
    }

    public ValidationError buildError(String errorCode) {
        String description = environment.getProperty(errorCode);
        return new ValidationError(errorCode, (description != null ? description : "Unknown error"));
    }
}
