package com.example.chap04.domain.mission.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MemberMissionRequestDTO {
    @Getter
    public static class MyMissionRequestDto {

        @NotNull(message = "회원 ID는 필수입니다.")
        private Long memberId;
    }
}
