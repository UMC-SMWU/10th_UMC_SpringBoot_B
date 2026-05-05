package com.example.umc10thsb.domain.review.controller;

import com.example.umc10thsb.domain.review.dto.ReviewReqDTO;
import com.example.umc10thsb.domain.review.dto.ReviewResDTO;
import com.example.umc10thsb.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10thsb.domain.review.service.ReviewService;
import com.example.umc10thsb.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Review", description = "리뷰 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores/{storeId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    // 가게 리뷰 작성
    @Operation(summary = "가게 리뷰 작성", description = "특정 가게에 대한 리뷰를 작성한다.")
    @PostMapping
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "1") Long memberId,
            @Valid @RequestBody ReviewReqDTO.CreateReview request
    ) {
        ReviewResDTO.CreateReview response = reviewService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED, response);
    }

    // 가게 리뷰 목록 조회 (페이지네이션)
    @Operation(summary = "가게 리뷰 목록 조회",
            description = "특정 가게의 리뷰 목록을 페이지네이션으로 조회한다.")
    @GetMapping
    public ApiResponse<ReviewResDTO.ReviewList> getReviews(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        ReviewResDTO.ReviewList response = reviewService.getStoreReviews(storeId, page, size);
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_LIST_SUCCESS, response);
    }
}
