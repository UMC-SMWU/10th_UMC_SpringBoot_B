package com.example._th.domain.review.dto;
import lombok.Builder;
import java.time.LocalDateTime;
public class ReviewResDTO {

    @Builder
    public record CreateReviewResultDTO(
            Long reviewId,
            LocalDateTime createdAt
    ) {}
}
