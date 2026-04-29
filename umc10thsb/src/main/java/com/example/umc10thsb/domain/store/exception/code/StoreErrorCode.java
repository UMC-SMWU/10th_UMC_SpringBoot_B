package com.example.umc10thsb.domain.store.exception.code;

import com.example.umc10thsb.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE_4041", "가게를 찾을 수 없습니다."),
    DUPLICATE_STORE(HttpStatus.CONFLICT, "STORE_4091", "이미 존재하는 가게입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
