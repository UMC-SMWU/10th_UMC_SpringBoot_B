package com.example.umc10thsb.global.apiPayload.exception;

import com.example.umc10thsb.global.apiPayload.ApiResponse;
import com.example.umc10thsb.global.apiPayload.code.BaseErrorCode;
import com.example.umc10thsb.global.apiPayload.code.GlobalErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
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

    // 2) @Valid @RequestBody 검증 실패 - Bean Validation 위반
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> fieldErrors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fe ->
                fieldErrors.put(fe.getField(),
                        fe.getDefaultMessage() == null ? "" : fe.getDefaultMessage())
        );

        log.warn("[MethodArgumentNotValid] {}", fieldErrors);

        return ResponseEntity
                .status(GlobalErrorCode.VALIDATION_ERROR.getHttpStatus())
                .body(ApiResponse.onFailure(GlobalErrorCode.VALIDATION_ERROR, fieldErrors));
    }

    // 3) Request Body JSON 파싱 실패
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException e, HttpServletRequest request) {
        log.warn("[MalformedRequestBody] {} {} → {}",
                request.getMethod(), request.getRequestURI(), e.getMostSpecificCause().getMessage());
        return ResponseEntity
                .status(GlobalErrorCode.MALFORMED_REQUEST_BODY.getHttpStatus())
                .body(ApiResponse.onFailure(
                        GlobalErrorCode.MALFORMED_REQUEST_BODY,
                        e.getMostSpecificCause().getMessage()));
    }

    // 4) @Validated 가 붙은 클래스의 @PathVariable / @RequestParam 제약 위반
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException e, HttpServletRequest request) {

        Map<String, String> violations = new LinkedHashMap<>();
        for (ConstraintViolation<?> cv : e.getConstraintViolations()) {
            String path = cv.getPropertyPath().toString();
            violations.put(path, cv.getMessage());
        }
        log.warn("[ConstraintViolation] {} {} → {}",
                request.getMethod(), request.getRequestURI(), violations);
        return ResponseEntity
                .status(GlobalErrorCode.CONSTRAINT_VIOLATION.getHttpStatus())
                .body(ApiResponse.onFailure(GlobalErrorCode.CONSTRAINT_VIOLATION, violations));
    }

    // 5) 필수 query parameter 누락
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> info = new LinkedHashMap<>();
        info.put(ex.getParameterName(), ex.getParameterType() + " 타입 파라미터가 필요합니다.");
        log.warn("[MissingRequestParameter] {}", info);
        return ResponseEntity
                .status(GlobalErrorCode.MISSING_REQUEST_PARAMETER.getHttpStatus())
                .body(ApiResponse.onFailure(GlobalErrorCode.MISSING_REQUEST_PARAMETER, info));
    }

    // 6) @PathVariable / @RequestParam 타입 변환 실패
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatch(
            MethodArgumentTypeMismatchException e, HttpServletRequest request) {

        Map<String, String> info = new LinkedHashMap<>();
        String expected = e.getRequiredType() != null ? e.getRequiredType().getSimpleName() : "unknown";
        info.put(e.getName(), "값 [" + e.getValue() + "] 을 " + expected + " 타입으로 변환할 수 없습니다.");
        log.warn("[TypeMismatch] {} {} → {}", request.getMethod(), request.getRequestURI(), info);
        return ResponseEntity
                .status(GlobalErrorCode.TYPE_MISMATCH.getHttpStatus())
                .body(ApiResponse.onFailure(GlobalErrorCode.TYPE_MISMATCH, info));
    }

    // 7) 그 외 모든 예외
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
