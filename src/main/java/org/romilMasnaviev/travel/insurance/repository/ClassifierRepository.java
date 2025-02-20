package org.romilMasnaviev.travel.insurance.repository;

import org.romilMasnaviev.travel.insurance.model.Classifier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassifierRepository extends JpaRepository<Classifier, Long> {
    Optional<Classifier> findByTitle(String title);
}
