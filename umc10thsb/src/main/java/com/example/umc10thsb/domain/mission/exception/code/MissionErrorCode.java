package com.example.umc10thsb.domain.mission.exception.code;

import com.example.umc10thsb.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION_4041", "미션을 찾을 수 없습니다."),
    MISSION_ALREADY_COMPLETED(HttpStatus.CONFLICT, "MISSION_4091", "이미 완료된 미션입니다."),
    INVALID_MISSION_STATUS(HttpStatus.BAD_REQUEST, "MISSION_4001", "유효하지 않은 미션 상태입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
