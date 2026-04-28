package com.example._th.domain.member.status;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum HomeSuccessCode {

    // 마이페이지/홈 조회 성공
    HOME_OK(HttpStatus.OK, "HOME200_1", "홈 화면 데이터를 성공적으로 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}