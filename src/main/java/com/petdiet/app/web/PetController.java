package com.petdiet.app.web;

import com.petdiet.app.service.WeightService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pet")
public class PetController {

    private final WeightService weightService;

    @PostMapping("/weight")
    public WeightResponse saveWeight(@RequestBody @Valid WeightRequest request) {
        try {
            Double updatedWeight = weightService.updateOrCreateWeight(
                    request.getMemberId(),
                    request.getPetId(),
                    request.getWeight()
            );

            return new WeightResponse("success", updatedWeight);

        } catch (IllegalStateException e) {
            return new WeightResponse("failure: " + e.getMessage(), null);
        } catch (Exception e) {
            return new WeightResponse("Unknown failure: " + e.getMessage(), null);
        }
    }
    @Data
    static class WeightRequest {
        @NotNull(message = "회원 ID는 필수입니다.")
        private Long memberId;

        @NotNull(message = "반려동물 ID는 필수입니다.")
        private Long petId;

        @NotNull(message = "몸무게 값은 필수입니다.")
        private Double weight;
    }

    @Data
    @AllArgsConstructor
    static class WeightResponse {
        private String status;
        private Double weight;
    }
}