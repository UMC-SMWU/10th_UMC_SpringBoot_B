package com.example.umc10thsb.domain.auth.exception.code;

import com.example.umc10thsb.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthSuccessCode implements BaseSuccessCode {

    AUTH_LOGIN_SUCCESS(HttpStatus.OK, "AUTH_2001", "로그인에 성공했습니다."),
    AUTH_LOGOUT_SUCCESS(HttpStatus.OK, "AUTH_2002", "로그아웃에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
