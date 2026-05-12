package com.example.chap04.domain.review.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPhotoInfo {
        private Long reviewPhotoId;
        private String photoUrl;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewReplyInfo {
        private Long replyId;
        private String content;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewInfo {
        private Long reviewId;
        private Long memberId;
        private String nickname;
        private Double star;
        private String content;
        private LocalDateTime createdAt;
        private List<ReviewPhotoInfo> photos;
        private ReviewReplyInfo reply;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewInfoList {
        private List<ReviewInfo> reviews;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MyReviewCursorResponse {

        private List<MyReviewDTO> contents;

        private Boolean hasNext;

        private Long nextCursorReviewId;

        private Double nextCursorStar;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MyReviewDTO {

        private Long reviewId;

        private String storeName;

        private Double star;

        private String content;

        private LocalDate createdAt;
    }
}