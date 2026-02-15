package com.petdiet.app.repository;

import com.petdiet.app.domain.Pet;
import com.petdiet.app.domain.PetWeight;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PetRepository {
    private final EntityManager em;

    public Pet findOne(Long id) { return em.find(Pet.class, id); }

}