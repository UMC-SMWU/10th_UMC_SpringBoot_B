package com.example.umc10thsb.domain.mission.exception;

import com.example.umc10thsb.global.apiPayload.code.BaseErrorCode;
import com.example.umc10thsb.global.security.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
