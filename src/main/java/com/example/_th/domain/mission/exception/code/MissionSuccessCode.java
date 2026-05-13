package com.example._th.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode {

    // 미션 목록 조회 성공
    MISSION_LIST_OK(HttpStatus.OK, "MISSION_200", "미션 목록을 성공적으로 가져왔습니다."),

    // 미션 완료(성공) 업데이트 성공
    MISSION_COMPLETE_OK(HttpStatus.OK, "MISSION_201", "미션을 성공적으로 완료 처리하였습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}