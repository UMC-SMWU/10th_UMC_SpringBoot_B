package com.example._th.domain.review.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode {

    // 리뷰 관련 성공 코드 정의
    REVIEW_CREATE_SUCCESS(HttpStatus.OK, "REVIEW200", "리뷰가 성공적으로 등록되었습니다."),
    REVIEW_UPDATE_SUCCESS(HttpStatus.OK, "REVIEW201", "리뷰가 성공적으로 수정되었습니다."),
    REVIEW_DELETE_SUCCESS(HttpStatus.OK, "REVIEW202", "리뷰가 성공적으로 삭제되었습니다."),
    REVIEW_GET_SUCCESS(HttpStatus.OK, "REVIEW203", "리뷰 조회가 완료되었습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}