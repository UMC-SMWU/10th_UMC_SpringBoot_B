package com.example.chap04.domain.mission.converter;

import com.example.chap04.domain.mission.dto.MemberMissionResponseDTO;
import com.example.chap04.domain.mission.entity.MemberMission;
import com.example.chap04.global.common.paging.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class MemberMissionConverter {
//    public static MissionResponseDTO.AchievedCountResponse toAchievedCountResponse(Long achievedCount) {
//        return MissionResponseDTO.AchievedCountResponse.builder()
//                .achievedCount(achievedCount)
//                .build();
//    }

    public static MemberMissionResponseDTO.MyMissionResponseDto toMyMissionResponseDto(
            MemberMission memberMission
    ) {
        return MemberMissionResponseDTO.MyMissionResponseDto.builder()
                .point(memberMission.getMission().getPoint())
                .storeName(memberMission.getMission().getStore().getName())
                .conditional(memberMission.getMission().getConditional())
                .isComplete(memberMission.getIsComplete())
                .build();
    }

    public static PageResponse<MemberMissionResponseDTO.MyMissionResponseDto> toMyMissionPageResponse(
            Page<MemberMission> memberMissionPage
    ) {
        List<MemberMissionResponseDTO.MyMissionResponseDto> items =
                memberMissionPage.getContent()
                        .stream()
                        .map(MemberMissionConverter::toMyMissionResponseDto)
                        .toList();

        return PageResponse.of(items, memberMissionPage);
    }
}
