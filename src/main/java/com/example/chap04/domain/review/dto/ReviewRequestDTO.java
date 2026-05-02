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
        private String content;
        private BigDecimal star;
        private Long storeId;
        private Long userId;
        private Long replyId;
        private List<String> photoUrls;
    }
}
