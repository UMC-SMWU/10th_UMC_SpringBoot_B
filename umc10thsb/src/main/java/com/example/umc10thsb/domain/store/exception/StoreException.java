package com.example.umc10thsb.domain.store.exception;

import com.example.umc10thsb.global.apiPayload.code.BaseErrorCode;
import com.example.umc10thsb.global.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
