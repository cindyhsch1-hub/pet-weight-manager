package com.petdiet.app.service;

import com.petdiet.app.domain.Pet;
import com.petdiet.app.domain.PetWeight;
import com.petdiet.app.exception.NoDataException;
import com.petdiet.app.repository.PetRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WeightService {

    private final PetRepository petRepository;

    @Transactional
    public Double updateOrCreateWeight(Long memberId, Long petId, Double weightValue) {
        Pet pet = petRepository.findOne(petId);

        validateOwnership(memberId, pet);

        pet.updateWeight(weightValue);

        return pet.getWeight();
    }

    private void validateOwnership(Long memberId, Pet pet) {
        if (pet == null) {
            throw new NoDataException("No pet exists");
        }
        if (!pet.getMember().getId().equals(memberId)) {
            throw new IllegalStateException("권한 없음");
        }
    }
}