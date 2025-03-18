package org.romilMasnaviev.travel.insurance.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ClassifierValueRepositoryTest {

    @Autowired
    private ClassifierValueRepository repository;

    @Test
    public void findByClassifierAndIc_WhenClassifierAndIcExist_ShouldReturnClassifierValue_TRAVEL_MEDICAL() {
        findByClassifierAndIc_WhenClassifierAndIcExist_ShouldReturnClassifierValue("RISK_TYPE", "TRAVEL_MEDICAL");
    }

    @Test
    public void findByClassifierAndIc_WhenClassifierAndIcExist_ShouldReturnClassifierValue_TRAVEL_LOSS_BAGGAGE() {
        findByClassifierAndIc_WhenClassifierAndIcExist_ShouldReturnClassifierValue("RISK_TYPE", "TRAVEL_LOSS_BAGGAGE");
    }

    @Test
    public void findByClassifierAndIc_WhenClassifierAndIcExist_ShouldReturnClassifierValue_TRAVEL_THIRD_PARTY_LIABILITY() {
        findByClassifierAndIc_WhenClassifierAndIcExist_ShouldReturnClassifierValue("RISK_TYPE", "TRAVEL_THIRD_PARTY_LIABILITY");
    }

    @Test
    public void findByClassifierAndIc_WhenClassifierAndIcExist_ShouldReturnClassifierValue_TRAVEL_EVACUATION() {
        findByClassifierAndIc_WhenClassifierAndIcExist_ShouldReturnClassifierValue("RISK_TYPE", "TRAVEL_EVACUATION");
    }

    @Test
    public void _findByClassifierAndIc_WhenClassifierAndIcExist_ShouldReturnClassifierValue_TRAVEL_SPORT_ACTIVITIES() {
        findByClassifierAndIc_WhenClassifierAndIcExist_ShouldReturnClassifierValue("RISK_TYPE", "TRAVEL_SPORT_ACTIVITIES");
    }

    @Test
    public void _findByClassifierAndIc_WhenClassifierAndIcExist_ShouldReturnClassifierValue_LATVIA() {
        findByClassifierAndIc_WhenClassifierAndIcExist_ShouldReturnClassifierValue("COUNTRY", "LATVIA");
    }

    @Test
    public void _findByClassifierAndIc_WhenClassifierAndIcExist_ShouldReturnClassifierValue_SPAIN() {
        findByClassifierAndIc_WhenClassifierAndIcExist_ShouldReturnClassifierValue("COUNTRY", "SPAIN");
    }

    @Test
    public void _findByClassifierAndIc_WhenClassifierAndIcExist_ShouldReturnClassifierValue_JAPAN() {
        findByClassifierAndIc_WhenClassifierAndIcExist_ShouldReturnClassifierValue("COUNTRY", "JAPAN");
    }

    @Test
    public void findByClassifierAndIc_WhenWrongClassifier_ShouldReturnEmptyOptional() {
        var classifierValueOptional = repository.findByClassifierAndIc("nonExisted", "TRAVEL_SPORT_ACTIVITIES");
        assertTrue(classifierValueOptional.isEmpty());
    }

    @Test
    public void findByClassifierAndIc_WhenWrongIc_ShouldReturnEmptyOptional() {
        var classifierValueOptional = repository.findByClassifierAndIc("RISK_TYPE", "nonExisted");
        assertTrue(classifierValueOptional.isEmpty());
    }

    @Test
    public void findByClassifierAndIc_WhenWrongClassifierAndIc_ShouldReturnEmptyOptional() {
        var classifierValueOptional = repository.findByClassifierAndIc("nonExisted", "nonExisted");
        assertTrue(classifierValueOptional.isEmpty());
    }



    private void findByClassifierAndIc_WhenClassifierAndIcExist_ShouldReturnClassifierValue(String classifierTitle, String ic) {
        var classifierValueOptional = repository.findByClassifierAndIc(classifierTitle, ic);
        assertTrue(classifierValueOptional.isPresent());
        assertEquals(ic,classifierValueOptional.get().getIc());
        assertEquals(classifierTitle, classifierValueOptional.get().getClassifier().getTitle());

    }


}