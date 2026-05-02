package com.example.umc10thsb.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GlobalSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "COMMON200", "성공입니다."),
    CREATED(HttpStatus.CREATED, "COMMON201", "리소스가 정상적으로 생성되었습니다."),
    NO_CONTENT(HttpStatus.NO_CONTENT, "COMMON204", "요청이 정상 처리되었으며 응답 본문이 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
