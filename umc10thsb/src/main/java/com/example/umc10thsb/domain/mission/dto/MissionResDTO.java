package com.example.umc10thsb.domain.mission.dto;

import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    // 단건 미션 목록
    @Builder
    public record MissionItem(
            Long missionId,
            String storeName,
            String title,
            String content,
            Integer reward,
            String status,
            String deadline
    ) {}

    // 페이지네이션된 미션 목록
    @Builder
    public record MissionList(
            List<MissionItem> missions,
            int page,
            int size,
            int totalPages,
            long totalElements,
            boolean hasNext
    ) {}

    // 미션 상세
    @Builder
    public record MissionDetail(
            Long missionId,
            Long storeId,
            String storeName,
            String title,
            String content,
            Integer reward,
            String status,
            String createdAt,
            String deadline
    ) {}

    // 미션 생성 응답
    @Builder
    public record CreateMission(
            Long missionId,
            Long storeId,
            String title
    ) {}

    // 미션 완료 처리 응답
    @Builder
    public record CompleteMission(
            Long missionId,
            String status,
            Integer rewardedPoint,
            Integer totalPoint
    ) {}

    // 홈 화면 - 동네별 미션 진행률
    @Builder
    public record HomeMissions(
            String address,
            long completedCount,
            long totalCount,
            List<MissionItem> missions
    ) {}
}
