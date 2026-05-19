package com.example._th.global.apiPayload.code;
import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {
    HttpStatus getStatus(); // 사진 image_33394e에서 이게 없어서 에러가 났던 겁니다!
    String getCode();
    String getMessage();
}