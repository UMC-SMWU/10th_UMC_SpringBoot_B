package com.example._th.domain.mission.exception;

import com.example._th.global.apiPayload.code.BaseErrorCode;
import com.example._th.global.apiPayload.exeption.ProjectException;

public class MissionException extends ProjectException {
    public MissionException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
