package com.example._th.domain.member.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode {

    // 회원가입 성공
    JOIN_OK(HttpStatus.OK, "MEMBER200_1", "성공적으로 회원가입이 완료되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
