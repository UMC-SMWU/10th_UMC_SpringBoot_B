package com.example._th.global.apiPayload.exception;

import com.example._th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectException extends RuntimeException {
    private final BaseErrorCode errorCode;
}