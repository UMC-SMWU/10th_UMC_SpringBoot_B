package com.example.umc10thsb.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode {

    // 4xx
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "요청한 리소스를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "COMMON405", "지원하지 않는 HTTP 메서드입니다."),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "COMMON422", "요청 값 검증에 실패했습니다."),
    MALFORMED_REQUEST_BODY(HttpStatus.BAD_REQUEST, "COMMON4001", "Request Body 형식이 올바르지 않습니다."),
    CONSTRAINT_VIOLATION(HttpStatus.BAD_REQUEST, "COMMON4002", "요청 파라미터 제약 조건을 위반했습니다."),
    MISSING_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "COMMON4003", "필수 요청 파라미터가 누락되었습니다."),
    TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "COMMON4004", "요청 파라미터의 타입이 올바르지 않습니다."),

    // 5xx
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
