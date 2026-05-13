package com.example._th.domain.review.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;
public class ReviewReqDTO {

    public record CreateReviewDTO(
            String content,
            Float star,
            List<String> reviewPhotos
    ) {}

    public record GetReviewListDTO(
            @NotNull Long memberId,   // 누구의 리뷰인지 (Request Body 조건)
            Long lastReviewId,        // 마지막으로 확인한 리뷰 ID (처음 조회시 null)
            @NotNull @Min(1) Integer size // 한 번에 가져올 개수
    ) {}
}
