package com.petdiet.app.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class PetWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weight_id")
    private Long id;

    private Double value;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;


    public static PetWeight createPetWeight(Pet pet, Double value) {
        PetWeight petWeight = new PetWeight();
        petWeight.setPet(pet);
        petWeight.setValue(value);
        petWeight.setDate(LocalDate.now());
        return petWeight;
    }
}