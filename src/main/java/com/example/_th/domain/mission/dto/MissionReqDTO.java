package com.example._th.domain.mission.dto;

public class MissionReqDTO {

    // 미션 목록 조회
    public record GetListDTO(
            String status // CHALLENGING 또는 COMPLETE
    ) {}

    // 미션 성공 업데이트 (단순 상태 변경 시 빈 데이터)
    public record UpdateCompleteDTO() {}
}