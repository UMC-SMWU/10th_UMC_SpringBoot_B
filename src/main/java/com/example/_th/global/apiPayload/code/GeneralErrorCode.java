package com.example._th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum GeneralErrorCode {




        // 1. 잘못된 요청 (400)
        BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400_1", "잘못된 요청입니다."),

        // 2. 인증되지 않음 (401)
        UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401_1", "인증되지 않았습니다."),

        // 3. 금지된 접근 (403)
        FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403_1", "접근이 금지되었습니다."),

        // 4. 리소스를 찾을 수 없음 (404)
        NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404_1", "해당 리소스를 찾을 수 없습니다.");

        private final HttpStatus status;
        private final String code;
        private final String message;

        // 인터페이스(BaseErrorCode)의 메서드들을 구현합니다.
        // @Getter 덕분에 getStatus(), getCode(), getMessage()가 자동으로 생성됩니다.
    }

