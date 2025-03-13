package org.romilMasnaviev.travel.insurance.repository;

import org.romilMasnaviev.travel.insurance.model.CountryDefaultDayRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryDefaultDayRateRepository extends JpaRepository<CountryDefaultDayRate, Long> {

    Optional<CountryDefaultDayRate> findByCountryIc(String country);
}
