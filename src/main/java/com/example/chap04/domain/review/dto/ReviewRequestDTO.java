package com.example.chap04.domain.review.dto;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MyReviewCursorRequest {

        @NotNull(message = "회원 ID는 필수입니다.")
        private Long memberId;

        /**
         * 마지막으로 조회한 리뷰 ID
         *
         * 첫 요청이면 null
         */
        private Long cursorReviewId;

        /**
         * 별점순 조회에서만 사용
         *
         * 첫 요청이면 null
         */
        private Double cursorStar;

        /**
         * 한 번에 조회할 개수
         */
        @Min(value = 1, message = "size는 1 이상이어야 합니다.")
        private Integer size;
    }
}
