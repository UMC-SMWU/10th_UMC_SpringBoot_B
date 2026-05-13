package com.example._th.domain.review.controller;
import com.example._th.domain.review.dto.ReviewReqDTO;
import com.example._th.domain.review.dto.ReviewResDTO;
import com.example._th.domain.review.service.ReviewService;
import com.example._th.domain.review.exception.code.ReviewSuccessCode;
import com.example._th.global.apiPayload.ApiResponse;
import com.example._th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
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

    // 미션 2: 내가 작성한 리뷰 목록 조회 (커서 기반 페이지네이션)
    @GetMapping("/me")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getMyReviewList(
            @Valid @RequestBody ReviewReqDTO.GetReviewListDTO request
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.REVIEW_LIST_OK, // 프로젝트의 SuccessCode에 맞춰 수정
                reviewService.getReviewList(request)
        );
    }
}

