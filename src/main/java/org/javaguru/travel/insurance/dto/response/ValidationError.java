package org.javaguru.travel.insurance.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError {
    private String errorCode;
    private String description;

    @Override
    public String toString() {
        return "ValidationError{" +
                "errorCode='" + errorCode + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
