package org.romilMasnaviev.travel.insurance.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import uk.org.webcompere.modelassert.json.JsonAssertions;

import static org.romilMasnaviev.travel.insurance.JsonFileReader.readJsonFromFile;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TravelCalculatePremiumControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void calculatePremium_whenValidData_ThenReturnValidResponse() throws Exception {
        calculatePremium("request-all-null.json", "response-all-null.json");
    }

    private void calculatePremium(String requestPath, String responsePath) throws Exception {
        MvcResult mvcResult = mvc.perform(post("/insurance/travel/")
                        .content(readJsonFromFile("controller/" + requestPath))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        JsonAssertions.assertJson(mvcResult.getResponse().getContentAsString())
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(readJsonFromFile("controller/" + responsePath));
    }
}