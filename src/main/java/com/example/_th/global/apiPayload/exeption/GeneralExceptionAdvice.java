package com.example._th.global.apiPayload.exeption;


import com.example._th.global.apiPayload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    // @Valid 검증 실패 시 발생하는 예외를 잡음
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {

        // 어떤 필드에서 에러가 났는지, 에러 메시지는 뭔지 가져옴
        String errorMessage = e.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        // 예쁘게 가공된 응답 리턴
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.onFailure("COMMON400", errorMessage, null));
    }
}