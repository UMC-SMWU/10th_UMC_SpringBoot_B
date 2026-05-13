package com.example._th.domain.review.exception;

import com.example._th.global.apiPayload.code.BaseErrorCode;
import com.example._th.global.apiPayload.exeption.ProjectException;

public class ReviewException extends ProjectException {
    public ReviewException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
