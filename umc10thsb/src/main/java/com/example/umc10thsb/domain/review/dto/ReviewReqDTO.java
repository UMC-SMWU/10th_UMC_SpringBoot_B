package com.example.umc10thsb.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    // 리뷰 작성
    public record CreateReview(
            @NotBlank String title,
            @NotNull @Min(1) @Max(5) Integer star,
            @NotBlank String content
    ) {}
}
