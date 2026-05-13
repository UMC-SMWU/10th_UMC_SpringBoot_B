package com.example._th.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;

public class MissionReqDTO {

    // 미션 목록 조회용 DTO
    public record GetListDTO(
            @NotBlank(message = "미션 상태(CHALLENGING/COMPLETE)는 필수 입력값입니다.")
            String status
    ) {}

    // 미션 성공 업데이트 (필요한 필드가 생기면 여기에 추가)
    public record UpdateCompleteDTO() {}
}