package org.romilMasnaviev.travel.insurance.repository;

import org.romilMasnaviev.travel.insurance.model.ClassifierValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClassifierValueRepository extends JpaRepository<ClassifierValue, Long> {

    @Query("select cv from ClassifierValue cv " +
            "left join Classifier c on cv.classifier.title = c.title " +
            "where c.title = :classifierTitle " +
            "and cv.ic = :ic")
    Optional<ClassifierValue> findByClassifierAndIc(@Param("classifierTitle") String classifierTitle,
                                                    @Param("ic") String ic);
}
