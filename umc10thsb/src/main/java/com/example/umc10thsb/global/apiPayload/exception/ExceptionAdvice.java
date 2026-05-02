package com.example.umc10thsb.global.apiPayload.exception;

import com.example.umc10thsb.global.apiPayload.ApiResponse;
import com.example.umc10thsb.global.apiPayload.code.BaseErrorCode;
import com.example.umc10thsb.global.apiPayload.code.GlobalErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    // 1) 비즈니스 예외
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<Object> handleGeneralException(
            GeneralException e, HttpServletRequest request) {
        BaseErrorCode code = e.getErrorCode();
        log.warn("[GeneralException] {} {} → {} {}",
                request.getMethod(), request.getRequestURI(),
                code.getCode(), code.getMessage());
        return ResponseEntity
                .status(code.getHttpStatus())
                .body(ApiResponse.onFailure(code));
    }

    // 2) @Valid 검증 실패
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> fieldErrors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fe ->
                fieldErrors.put(fe.getField(),
                        fe.getDefaultMessage() == null ? "" : fe.getDefaultMessage())
        );

        return ResponseEntity
                .status(GlobalErrorCode.VALIDATION_ERROR.getHttpStatus())
                .body(ApiResponse.onFailure(GlobalErrorCode.VALIDATION_ERROR, fieldErrors));
    }

    // 3) 그 외 모든 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(
            Exception e, HttpServletRequest request) {
        log.error("[UnhandledException] {} {}",
                request.getMethod(), request.getRequestURI(), e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.onFailure(GlobalErrorCode.INTERNAL_SERVER_ERROR, e.getMessage()));
    }
}
