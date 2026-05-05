package com.example.chap04.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProjectException extends RuntimeException {
    private final BaseCode errorCode;
}
