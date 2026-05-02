package com.example.umc10thsb.domain.review.exception.code;

import com.example.umc10thsb.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATED(HttpStatus.CREATED, "REVIEW_2011", "리뷰가 정상적으로 생성되었습니다."),
    REVIEW_LIST_SUCCESS(HttpStatus.OK, "REVIEW_2001", "리뷰 목록 조회에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
