package com.example.chap04.domain.food.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FoodRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberFoodRequest {
        private Long memberId;
        private Long foodId;
    }
}
