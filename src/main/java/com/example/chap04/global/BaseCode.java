package com.example.chap04.global;

import org.springframework.http.HttpStatus;

public interface BaseCode {
    HttpStatus getStatus();
    String getCode();
    String getMessage();
}
