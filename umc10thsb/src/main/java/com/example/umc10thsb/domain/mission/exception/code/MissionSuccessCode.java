package com.example.umc10thsb.domain.mission.exception.code;

import com.example.umc10thsb.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_LIST_SUCCESS(HttpStatus.OK, "MISSION_2001", "내 미션 목록 조회에 성공했습니다."),
    MISSION_DETAIL_SUCCESS(HttpStatus.OK, "MISSION_2002", "미션 상세 조회에 성공했습니다."),
    MISSION_COMPLETE_SUCCESS(HttpStatus.OK, "MISSION_2003", "미션을 완료 처리했습니다."),
    MISSION_HOME_SUCCESS(HttpStatus.OK, "MISSION_2004", "홈 화면 미션 진행률 조회에 성공했습니다."),
    MISSION_CHALLENGING_LIST_SUCCESS(HttpStatus.OK, "MISSION_2005", "내가 진행중인 미션 목록 조회에 성공했습니다."),
    MISSION_CREATED(HttpStatus.CREATED, "MISSION_2011", "미션이 정상적으로 생성되었습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
