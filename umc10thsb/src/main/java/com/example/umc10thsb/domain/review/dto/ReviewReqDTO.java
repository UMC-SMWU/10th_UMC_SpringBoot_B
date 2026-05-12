package com.example.umc10thsb.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReviewReqDTO {

    // 리뷰 작성 요청
    public record CreateReview(

            @NotBlank(message = "title은 비어있을 수 없습니다.")
            @Size(min = 1, max = 100, message = "title은 1자 이상 100자 이하여야 합니다.")
            String title,

            @NotNull(message = "star는 필수입니다.")
            @Min(value = 1, message = "별점은 1점 이상이어야 합니다.")
            @Max(value = 5, message = "별점은 5점 이하여야 합니다.")
            Integer star,

            @NotBlank(message = "content는 비어있을 수 없습니다.")
            @Size(max = 1000, message = "content는 1000자를 넘을 수 없습니다.")
            String content
    ) {}
}
