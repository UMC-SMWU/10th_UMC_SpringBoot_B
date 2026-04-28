package com.example._th.domain.review.dto;
import java.util.List;
public class ReviewReqDTO {

    public record CreateReviewDTO(
            String content,
            Float star,
            List<String> reviewPhotos
    ) {}
}
