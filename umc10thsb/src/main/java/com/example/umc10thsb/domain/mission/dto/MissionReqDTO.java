package com.example.umc10thsb.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MissionReqDTO {

    // 가게에 미션 추가
    public record CreateMission(
            @NotBlank String title,
            @NotBlank String content,
            @NotNull @Min(0) Integer reward,
            @NotBlank String deadline
    ) {}
}
