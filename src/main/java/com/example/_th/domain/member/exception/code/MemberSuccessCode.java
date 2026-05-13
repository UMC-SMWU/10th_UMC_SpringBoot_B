package com.example._th.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode {

    // 200 OK: 일반적인 성공
    MEMBER_LOGIN_OK(HttpStatus.OK, "MEMBER_200", "로그인에 성공했습니다."),
    MEMBER_UPDATE_OK(HttpStatus.OK, "MEMBER_201", "회원 정보가 수정되었습니다."),

    // 201 Created: 자원 생성 성공 (회원가입 등)
    JOIN_OK(HttpStatus.CREATED, "MEMBER_202", "회원가입이 완료되었습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}