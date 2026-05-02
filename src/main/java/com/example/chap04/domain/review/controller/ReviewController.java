package com.example.chap04.domain.review.controller;

import com.example.chap04.domain.review.dto.ReviewRequestDTO;
import com.example.chap04.domain.review.dto.ReviewResponseDTO;
import com.example.chap04.domain.review.service.ReviewService;
import com.example.chap04.global.ApiResponse;
import com.example.chap04.global.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<ReviewResponseDTO.ReviewInfo>> createRiview (
            @RequestBody ReviewRequestDTO.CreateReviewRequest request
    ) {
        ReviewResponseDTO.ReviewInfo result = reviewService.createReview(request);
        return ApiResponse.onSuccessResponse(GeneralSuccessCode.POST_SUCCESS, result);
    }
}
