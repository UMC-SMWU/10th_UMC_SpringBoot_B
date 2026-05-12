package com.example.umc10thsb.domain.review.exception.code;

import com.example.umc10thsb.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW_4041", "리뷰를 찾을 수 없습니다."),
    INVALID_STAR_RATING(HttpStatus.BAD_REQUEST, "REVIEW_4001", "별점은 1점 이상 5점 이하여야 합니다."),
    INVALID_REVIEW_SORT_BY(HttpStatus.BAD_REQUEST, "REVIEW_4002", "지원하지 않는 정렬 기준입니다."),
    INVALID_CURSOR(HttpStatus.BAD_REQUEST, "REVIEW_4003", "STAR 정렬에서는 cursorStar 와 cursorId 가 함께 전달되어야 합니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
