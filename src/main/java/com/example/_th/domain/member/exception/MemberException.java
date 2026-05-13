package com.example._th.domain.member.exception;

import com.example._th.global.apiPayload.code.BaseErrorCode;
import com.example._th.global.apiPayload.exeption.ProjectException;

public class MemberException extends ProjectException {
    public MemberException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
