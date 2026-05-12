package com.example.umc10thsb.domain.store.exception.code;

import com.example.umc10thsb.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    STORE_CREATED(HttpStatus.CREATED, "STORE_2011", "가게가 정상적으로 생성되었습니다."),
    STORE_REVIEW_LIST_SUCCESS(HttpStatus.OK, "STORE_2001", "가게 리뷰 목록 조회에 성공했습니다."),
    STORE_MISSION_LIST_SUCCESS(HttpStatus.OK, "STORE_2002", "가게 미션 목록 조회에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
