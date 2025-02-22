package org.romilMasnaviev.travel.insurance.repository;

import org.romilMasnaviev.travel.insurance.model.ClassifierValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class ClassifierValueRepositoryTest {

    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    private static Stream<Arguments> riskTypesValues() {
        return Stream.of(Arguments.of("RISK_TYPE", "TRAVEL_MEDICAL"),
                Arguments.of("RISK_TYPE", "TRAVEL_CANCELLATION"),
                Arguments.of("RISK_TYPE", "TRAVEL_THIRD_PARTY_LIABILITY"),
                Arguments.of("RISK_TYPE", "TRAVEL_LOSS_BAGGAGE"),
                Arguments.of("RISK_TYPE", "TRAVEL_EVACUATION"),
                Arguments.of("RISK_TYPE", "TRAVEL_SPORT_ACTIVITIES"));
    }

    @Test
    @DisplayName("Test: classifierValue table is present")
    public void testInjectedRepository_WhenAutowired_ThenIsNotNull() {
        assertNotNull(classifierValueRepository);
    }

    @Test
    @DisplayName("Test: Can't find record by classifier(doesn't exist) and ic(doesn't exist)")
    public void testFindByClassifierAndIc_WhenRecordDoesntExist_ThenReturnEmptyOptional() {
        Optional<ClassifierValue> classifierValueOptional = classifierValueRepository.findByClassifierAndIc("DOESNT_EXISTS", "DOESNT_EXISTS");

        assertTrue(classifierValueOptional.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("riskTypesValues")
    public void testFindByClassifierAndIc(String classifierTitle, String ic) {
        Optional<ClassifierValue> classifierValueOptional = classifierValueRepository.findByClassifierAndIc(classifierTitle, ic);

        assertTrue(classifierValueOptional.isPresent());
        assertEquals(classifierValueOptional.get().getClassifier().getTitle(), classifierTitle);
        assertEquals(classifierValueOptional.get().getIc(), ic);
    }

}