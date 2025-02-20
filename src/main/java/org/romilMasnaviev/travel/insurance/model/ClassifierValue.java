package org.romilMasnaviev.travel.insurance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "classifier_values")
public class ClassifierValue {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "classifier_id", referencedColumnName = "id", nullable = false)
    private Classifier classifier;

    @Column(name = "ic", nullable = false)
    private String ic;

    @Column(name = "description", nullable = false)
    private String description;
}
