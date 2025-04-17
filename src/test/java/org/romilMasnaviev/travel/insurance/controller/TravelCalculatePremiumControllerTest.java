package org.romilMasnaviev.travel.insurance.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.romilMasnaviev.travel.insurance.dto.request.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.romilMasnaviev.travel.insurance.TestUtils.futureDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TravelCalculatePremiumControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void calculatePremium_whenValidData_ThenReturnValidResponse() throws Exception {
        mvc.perform(post("/insurance/travel/")
                .content(String.valueOf(TravelCalculatePremiumRequest.builder()
                        .country("LATVIA")
                        .agreementDateFrom(futureDate(1))
                        .agreementDateFrom(futureDate(2))
                        .personFirstName("firstName")
                        .personLastName("lastName")
                        .selectedRisks(List.of("")))))

    }


}