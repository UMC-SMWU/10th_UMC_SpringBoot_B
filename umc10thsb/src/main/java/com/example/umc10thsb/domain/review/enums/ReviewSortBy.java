package com.example.umc10thsb.domain.review.enums;

import com.example.umc10thsb.domain.review.exception.ReviewException;
import com.example.umc10thsb.domain.review.exception.code.ReviewErrorCode;

// 내가 작성한 리뷰 조회 정렬 기준
public enum ReviewSortBy {
    ID,
    STAR;

    public static ReviewSortBy from(String value) {
        if (value == null || value.isBlank()) {
            return ID;
        }
        try {
            return ReviewSortBy.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ReviewException(ReviewErrorCode.INVALID_REVIEW_SORT_BY);
        }
    }
}
