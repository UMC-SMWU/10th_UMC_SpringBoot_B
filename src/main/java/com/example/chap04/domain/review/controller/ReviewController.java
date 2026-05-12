package com.example.chap04.domain.review.controller;

import com.example.chap04.domain.review.dto.ReviewRequestDTO;
import com.example.chap04.domain.review.dto.ReviewResponseDTO;
import com.example.chap04.domain.review.service.ReviewService;
import com.example.chap04.global.api.ApiResponse;
import com.example.chap04.global.api.GeneralSuccessCode;
import com.example.chap04.global.common.paging.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(
            summary = "가게별 리뷰 목록 조회",
            description = "특정 가게의 리뷰 목록을 페이징하여 조회합니다."
    )
    @GetMapping("/stores/{storeId}")
    public ResponseEntity<ApiResponse<PageResponse<ReviewResponseDTO.ReviewInfo>>> getStoreReviews(
            @Parameter(description = "가게 ID", example = "1")
            @PathVariable Long storeId,

            @Parameter(description = "페이지 번호, 0부터 시작", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "한 페이지에 조회할 리뷰 개수", example = "10")
            @RequestParam(defaultValue = "10") int size,

            @Parameter(description = "정렬 기준 필드", example = "createdAt")
            @RequestParam(defaultValue = "createdAt") String sort
    ) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.DESC, sort)
        );

        PageResponse<ReviewResponseDTO.ReviewInfo> result =
                reviewService.getReviewListByStoreId(storeId, pageable);

        return ApiResponse.onSuccessResponse(GeneralSuccessCode.GET_SUCCESS, result);
    }

    /**
     * 내가 작성한 리뷰 조회 - ID순
     */
    @PostMapping("/my/id")
    public ResponseEntity<ApiResponse<ReviewResponseDTO.MyReviewCursorResponse>> getMyReviewsById(
            @RequestBody @Valid ReviewRequestDTO.MyReviewCursorRequest request
    ) {
        ReviewResponseDTO.MyReviewCursorResponse result =
                reviewService.getMyReviewsById(request);

        return ApiResponse.onSuccessResponse(
                GeneralSuccessCode.GET_SUCCESS,
                result
        );
    }

    /**
     * 내가 작성한 리뷰 조회 - 별점순
     */
    @PostMapping("/my/star")
    public ResponseEntity<ApiResponse<ReviewResponseDTO.MyReviewCursorResponse>> getMyReviewsByStar(
            @RequestBody @Valid ReviewRequestDTO.MyReviewCursorRequest request
    ) {
        ReviewResponseDTO.MyReviewCursorResponse result =
                reviewService.getMyReviewsByStar(request);

        return ApiResponse.onSuccessResponse(
                GeneralSuccessCode.GET_SUCCESS,
                result
        );
    }
}