package com.example.chap04.domain.review.dto;

import java.math.BigDecimal;
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
    public static class ReviewInfo {
        private Long reviewId;
        private String content;
        private BigDecimal star;
        private LocalDateTime createdAt;
        private Long userId;
        private Long storeId;
        private Long replyId;
        private List<ReviewPhotoInfo> photos;
    }
}
