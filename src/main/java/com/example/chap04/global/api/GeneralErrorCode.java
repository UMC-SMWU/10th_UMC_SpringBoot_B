package com.example.chap04.global.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GeneralErrorCode implements BaseCode {

    // Auth
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "409", "이미 존재하는 이메일입니다."),

    // Member
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "404", "해당 멤버를 찾을 수 없습니다"),

    // Basic
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "401", "인증되지 않았습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "403", "접근이 금지되었습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "404", "해당 리소스를 찾을 수 없습니다"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "서버 내부 오류입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
