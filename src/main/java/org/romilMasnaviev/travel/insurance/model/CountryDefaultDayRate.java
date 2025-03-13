package org.romilMasnaviev.travel.insurance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "country_default_day_rate")
public class CountryDefaultDayRate {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "country_ic")
    private String countryIc;
    @Column(name = "default_day_rate")
    private BigDecimal defaultDayRate;
}
