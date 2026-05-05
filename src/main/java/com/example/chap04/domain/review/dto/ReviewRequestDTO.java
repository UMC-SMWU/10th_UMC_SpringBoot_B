package com.example.chap04.domain.review.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewRequest {
        private Long userId;
        private Long storeId;
        private int point;
        private String content;
        private List<String> reviewImages;
    }
}
