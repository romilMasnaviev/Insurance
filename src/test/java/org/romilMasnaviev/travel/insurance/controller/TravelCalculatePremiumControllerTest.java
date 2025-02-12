package org.romilMasnaviev.travel.insurance.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.romilMasnaviev.travel.insurance.JsonFileReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@SpringBootTest
@AutoConfigureMockMvc
class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private JsonFileReader reader;

    @Test
    void testCalculatePremium_WhenGivenValidData_ThenReturnResponse() throws Exception {
        executeRequestAndCompare(
                "controller/request-valid.json",
                "controller/response-valid.json");
    }

    @Test
    void testCalculatePremium_WhenAllFieldsIsNull_ThenReturnErrors() throws Exception {
        executeRequestAndCompare(
                "controller/request-all-null.json",
                "controller/response-all-null.json");
    }

    @Test
    void testCalculatePremium_WhenFirstNameIsNull_ThenReturnResponse() throws Exception {
        executeRequestAndCompare(
                "controller/request-firstname-null.json",
                "controller/response-firstname-null.json");
    }

    @Test
    void testCalculatePremium_WhenLastNameIsNull_ThenReturnResponse() throws Exception {
        executeRequestAndCompare(
                "controller/request-lastname-null.json",
                "controller/response-lastname-null.json");
    }

    @Test
    void testCalculatePremium_WhenDateFromIsNull_ThenReturnResponse() throws Exception {
        executeRequestAndCompare(
                "controller/request-datefrom-null.json",
                "controller/response-datefrom-null.json");
    }

    @Test
    void testCalculatePremium_WhenDateToIsNull_ThenReturnResponse() throws Exception {
        executeRequestAndCompare(
                "controller/request-dateto-null.json",
                "controller/response-dateto-null.json");
    }


    @Test
    void testCalculatePremium_WhenDateToLessThanDateFrom_ThenReturnResponse() throws Exception {
        executeRequestAndCompare(
                "controller/request-invalid-dates.json",
                "controller/response-invalid-dates.json");
    }

    @Test
    void testCalculatePremium_WhenDateToAndDateFromInPast_ThenReturnResponse() throws Exception {
        executeRequestAndCompare(
                "controller/request-invalid-dates.json",
                "controller/response-invalid-dates.json");
    }

    @Test
    void testCalculatePremium_WhenDateFromInPast_ThenReturnResponse() throws Exception {
        executeRequestAndCompare(
                "controller/request-date-from-past.json",
                "controller/response-date-from-past.json");
    }

    @Test
    void testCalculatePremium_WhenSelectedRisksIsNull_ThenReturnResponse() throws Exception {
        executeRequestAndCompare(
                "controller/request-selectedrisk-null.json",
                "controller/response-selectedrisk-null.json");
    }

    private void executeRequestAndCompare(String requestPath, String responsePath) throws Exception {
        String jsonRequest = reader.readJsonFromFile(requestPath);
        String expectedResponse = reader.readJsonFromFile(responsePath);

        MvcResult mvcResult = mockMvc.perform(post("/insurance/travel/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();
        assertJson(expectedResponse)
                .where()
                .arrayInAnyOrder()
                .keysInAnyOrder()
                .isEqualTo(actualResponse);

    }
}