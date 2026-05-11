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

    // 내가 진행중인 미션 목록 조회
    public record GetChallengingMissions(
            @NotNull(message = "memberId는 필수입니다.") @Min(1) Long memberId,
            Integer page,
            Integer size
    ) {
        public GetChallengingMissions {
            if (page == null || page < 1) page = 1;
            if (size == null || size < 1) size = 10;
        }
    }
}
