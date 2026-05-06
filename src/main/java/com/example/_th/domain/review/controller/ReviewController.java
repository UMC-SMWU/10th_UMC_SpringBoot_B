package com.example._th.domain.review.controller;
import com.example._th.domain.review.dto.ReviewReqDTO;
import com.example._th.domain.review.dto.ReviewResDTO;
import com.example._th.domain.review.service.ReviewService;
import com.example._th.domain.review.status.ReviewSuccessCode;
import com.example._th.global.apiPayload.ApiResponse;
import com.example._th.global.apiPayload.code.BaseSuccessCode; // 이거 필수!
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class ReviewController {

    private final ReviewService reviewService;


    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
            @PathVariable(name = "storeId") Long storeId,
            @RequestBody ReviewReqDTO.CreateReviewDTO request
    ) {
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_CREATE_OK;
        return ApiResponse.onSuccess(code, reviewService.createReview(storeId, request));
    }
}