package com.example.chap04.global.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProjectException extends RuntimeException {
    // RuntimeException 발생시, BaseCode를 지킨 형식 지정. -> Custom Exception 만든 것.
    private final BaseCode errorCode;
}
