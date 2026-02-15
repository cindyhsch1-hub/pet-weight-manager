package com.petdiet.app.service;

import com.petdiet.app.domain.Pet;
import com.petdiet.app.domain.PetWeight;
import com.petdiet.app.repository.PetRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WeightService {
    private final PetRepository petRepository;

    @Transactional
    public Double updateOrCreateWeight(Long memberId, Long petId, Double weightValue) {
        Pet pet = petRepository.findOne(petId);

        if (!pet.getMember().getId().equals(memberId)) throw new IllegalStateException("권한 없음");

        List<PetWeight> weights = petRepository.findTodayWeight(petId, LocalDate.now());

        if (!weights.isEmpty()) {

            weights.get(0).setValue(weightValue);
            return weights.get(0).getValue();
        } else {

            PetWeight pw = PetWeight.createPetWeight(pet, weightValue);
            petRepository.saveWeight(pw);
            return pw.getValue();
        }
    }
}