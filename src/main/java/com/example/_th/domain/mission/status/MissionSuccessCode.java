package com.example._th.domain.mission.status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum MissionSuccessCode {

    MISSION_LIST_OK(HttpStatus.OK, "MISSION200_1", "미션 목록을 성공적으로 조회했습니다."),


    MISSION_COMPLETE_OK(HttpStatus.OK, "MISSION200_2", "미션을 성공적으로 완료 처리했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
