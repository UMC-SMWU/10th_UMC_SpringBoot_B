package com.example._th.global.apiPayload.handler;

import com.example._th.global.apiPayload.ApiResponse;
import com.example._th.global.apiPayload.code.BaseErrorCode;
import com.example._th.global.apiPayload.code.status.ErrorStatus; // 에러코드 모음
import com.example._th.global.apiPayload.exception.ProjectException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    // 1. 프로젝트 내에서 정의한 예외(ProjectException) 처리
    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ApiResponse<Void>> handleProjectException(ProjectException e) {
        BaseErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ApiResponse.onFailure(errorCode.getCode(), errorCode.getMessage(), null));
    }

    // 2. 그 외 정의되지 않은 모든 일반 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {
        // INTERNAL_SERVER_ERROR 같은 기본 에러 코드를 사용합니다.
        return ResponseEntity.status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.onFailure("COMMON500", ex.getMessage(), null));
    }
}