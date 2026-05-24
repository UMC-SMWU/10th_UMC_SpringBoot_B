package com.example.umc10thsb.domain.review.exception;

import com.example.umc10thsb.global.apiPayload.code.BaseErrorCode;
import com.example.umc10thsb.global.security.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
