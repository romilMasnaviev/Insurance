package org.romilMasnaviev.travel.insurance.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class TravelCalculatePremiumResponse extends CoreResponse {
    private String personFirstName;
    private String personLastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateTo;
    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse(List<ValidationError> errors) {
        super(errors);
    }
}
