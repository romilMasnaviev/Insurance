package org.romilMasnaviev.travel.insurance.dto.response;

public record ValidationError(String errorCode, String description) {
    @Override
    public String toString() {
        return "ValidationError{" +
                "errorCode='" + errorCode + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
