package com.example.chap04.domain.food.dto;

import com.example.chap04.domain.food.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FoodResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FoodInfo {
        private Long foodId;
        private Food.FoodCategory name;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberFoodInfo {
        private Long memberFoodId;
        private Long memberId;
        private FoodInfo food;
    }
}
