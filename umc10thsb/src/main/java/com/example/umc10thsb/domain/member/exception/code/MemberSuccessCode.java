package com.example.umc10thsb.domain.member.exception.code;

import com.example.umc10thsb.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    MEMBER_SIGNUP_SUCCESS(HttpStatus.CREATED, "MEMBER_2011", "회원가입에 성공했습니다."),
    MEMBER_INFO_SUCCESS(HttpStatus.OK, "MEMBER_2001", "마이페이지 조회에 성공했습니다."),
    MEMBER_UPDATE_SUCCESS(HttpStatus.OK, "MEMBER_2002", "마이페이지 수정에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
