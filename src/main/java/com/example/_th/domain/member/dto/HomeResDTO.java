package com.example._th.domain.member.dto;

import lombok.Builder;

public class HomeResDTO {

    @Builder
    public record HomeViewDTO(
            String nickname,
            Integer point,
            Integer challengingMissionCount
    ) {}
}

