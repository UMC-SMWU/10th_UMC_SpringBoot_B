package com.example.chap04.domain.store.exception;

import com.example.chap04.global.api.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements BaseCode {
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_1", "해당 가게를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String Code;
    private final String message;
}
