package com.example.umc10thsb.domain.review.controller;

import com.example.umc10thsb.domain.review.dto.ReviewResDTO;
import com.example.umc10thsb.domain.review.enums.ReviewSortBy;
import com.example.umc10thsb.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10thsb.domain.review.service.ReviewService;
import com.example.umc10thsb.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 내가 작성한 리뷰 조회 컨트롤러
@Tag(name = "MyReview", description = "내가 작성한 리뷰 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class MyReviewController {

    private final ReviewService reviewService;

    @Operation(
            summary = "내가 작성한 리뷰 목록 조회"
    )
    @GetMapping("/me")
    public ApiResponse<ReviewResDTO.MyReviewCursorList> getMyReviews(
            @Parameter(description = "회원 ID (필수, 하드코딩 X)", required = true)
            @RequestParam Long memberId,

            @Parameter(description = "정렬 기준: ID(기본) | STAR")
            @RequestParam(required = false, defaultValue = "ID") String sortBy,

            @Parameter(description = "이전 응답의 nextCursorId. 첫 요청에서는 생략한다.")
            @RequestParam(required = false) Long cursorId,

            @Parameter(description = "STAR 정렬에서만 사용. 이전 응답의 nextCursorStar.")
            @RequestParam(required = false) Integer cursorStar,

            @Parameter(description = "페이지 크기 (default 10)")
            @RequestParam(required = false, defaultValue = "10") @Min(1) int size
    ) {
        ReviewSortBy sortByEnum = ReviewSortBy.from(sortBy);
        ReviewResDTO.MyReviewCursorList response =
                reviewService.getMyReviews(memberId, sortByEnum, cursorId, cursorStar, size);
        return ApiResponse.onSuccess(ReviewSuccessCode.MY_REVIEW_LIST_SUCCESS, response);
    }
}
