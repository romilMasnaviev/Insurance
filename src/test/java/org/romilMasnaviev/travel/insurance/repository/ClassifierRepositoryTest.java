package org.romilMasnaviev.travel.insurance.repository;

import org.romilMasnaviev.travel.insurance.model.Classifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class ClassifierRepositoryTest {

    @Autowired
    private ClassifierRepository classifierRepository;

    @Test
    @DisplayName("Test: classifier table is present")
    public void testInjectedRepository_WhenAutowired_ThenIsNotNull() {
        assertNotNull(classifierRepository);
    }

    @Test
    @DisplayName("Test: Can find record by Title")
    public void testFindRecordByTitle_WhenTitleExists_ThenReturnRecord() {
        Optional<Classifier> classifierOptional = classifierRepository.findByTitle("RISK_TYPE");

        assertTrue(classifierOptional.isPresent());
        assertEquals("RISK_TYPE", classifierOptional.get().getTitle());
    }

    @Test
    @DisplayName("Test: Can't find record by title")
    public void testFindRecordByTittle_WhenTitleDoesntExist_ThenReturnEmptyOptional() {
        Optional<Classifier> classifierOptional = classifierRepository.findByTitle("DOESNT_EXISTS");

        assertTrue(classifierOptional.isEmpty());
    }
}