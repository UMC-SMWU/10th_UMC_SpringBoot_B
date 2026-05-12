package com.example.umc10thsb.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewResDTO {

    // 리뷰 작성 응답
    @Builder
    public record CreateReview(
            Long reviewId,
            Long storeId,
            String title,
            Integer star,
            String content
    ) {}

    // 리뷰 목록 element
    @Builder
    public record ReviewItem(
            Long reviewId,
            String writerName,
            Integer star,
            String title,
            String content,
            String createdAt
    ) {}

    // 페이지네이션된 리뷰 목록
    @Builder
    public record ReviewList(
            List<ReviewItem> reviews,
            int page,
            int size,
            int totalPages,
            long totalElements,
            boolean hasNext
    ) {}
}
