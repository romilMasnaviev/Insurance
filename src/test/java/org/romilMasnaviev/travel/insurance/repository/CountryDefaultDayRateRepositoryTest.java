package org.romilMasnaviev.travel.insurance.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class CountryDefaultDayRateRepositoryTest {

    @Autowired
    CountryDefaultDayRateRepository repository;

    @Test
    public void findByClassifierAndIc_WhenCountryIcExist_ShouldReturnCountryValue_LATVIA() {
        findByClassifierAndIc_WhenCountryIcExist_ShouldReturnCountryValue("LATVIA", 1.0f);
    }

    @Test
    public void findByClassifierAndIc_WhenCountryIcExist_ShouldReturnCountryValue_SPAIN() {
        findByClassifierAndIc_WhenCountryIcExist_ShouldReturnCountryValue("SPAIN", 2.5f);
    }

    @Test
    public void findByClassifierAndIc_WhenCountryIcExist_ShouldReturnCountryValue_JAPAN() {
        findByClassifierAndIc_WhenCountryIcExist_ShouldReturnCountryValue("JAPAN", 3.5f);
    }

    @Test
    public void findByClassifierAndIc_WhenWrongCountryIc_ShouldReturnEmptyOptional() {
        var countryIcOptional = repository.findByCountryIc("NON_EXISTED");
        assertTrue(countryIcOptional.isEmpty());
    }

    private void findByClassifierAndIc_WhenCountryIcExist_ShouldReturnCountryValue(String countryIc, float dayRate) {
        var countryIcOptional = repository.findByCountryIc(countryIc);
        assertTrue(countryIcOptional.isPresent());
        assertEquals(countryIc, countryIcOptional.get().getCountryIc());
        assertEquals(BigDecimal.valueOf(dayRate), countryIcOptional.get().getDefaultDayRate().setScale(1, RoundingMode.HALF_UP));
    }
}