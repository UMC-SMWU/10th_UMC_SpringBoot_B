package com.example._th.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {
        // HTTP 상태 코드를 반환 (예: OK, CREATED)
        HttpStatus getStatus();

        // 우리 서버에서 정한 성공 코드 문자열을 반환 (예: "COMMON200")
        String getCode();

        // 성공 메시지를 반환 (예: "요청에 성공하였습니다.")
        String getMessage();
    }
}
