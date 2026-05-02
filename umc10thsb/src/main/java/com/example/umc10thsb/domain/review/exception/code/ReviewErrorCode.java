package com.example.umc10thsb.domain.review.exception.code;

import com.example.umc10thsb.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW_4041", "리뷰를 찾을 수 없습니다."),
    INVALID_STAR_RATING(HttpStatus.BAD_REQUEST, "REVIEW_4001", "별점은 1점 이상 5점 이하여야 합니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
