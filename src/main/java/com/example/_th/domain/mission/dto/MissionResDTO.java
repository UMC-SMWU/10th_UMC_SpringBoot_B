package com.example._th.domain.mission.dto;

import lombok.Builder;
import java.util.List;
import lombok.Builder;             // @Builder 빨간 줄 해결
import java.time.LocalDateTime;
public class MissionResDTO {

    @Builder
    public record MissionDTO(
            Long missionId,
            String title,
            Integer reward,
            String deadline
    ) {}

    @Builder
    public record MissionPreViewListDTO(
            List<MissionDTO> missionList,
            Integer listSize
    ) {}

    @Builder
    public record MissionCompleteResultDTO(
            Long memberMissionId,
            String status, // "COMPLETE"
            LocalDateTime completedAt
    ) {}
}