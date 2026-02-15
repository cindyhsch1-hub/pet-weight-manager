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

    public List<PetWeight> findTodayWeight(Long petId, LocalDate date) {
        return em.createQuery("select pw from PetWeight pw where pw.pet.id = :petId and pw.date = :date", PetWeight.class)
                .setParameter("petId", petId)
                .setParameter("date", date)
                .getResultList();
    }

    public void saveWeight(PetWeight pw) { em.persist(pw); }
}