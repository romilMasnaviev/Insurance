package org.romilMasnaviev.travel.insurance.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TravelCalculatePremiumRequest {
    private String personFirstName;
    private String personLastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    //Дата начала договора
    private Date agreementDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    //Дата окончания договора
    private Date agreementDateTo;
    @JsonProperty("selected_risks")
    private List<String> selectedRisks;
    private String country;
}
