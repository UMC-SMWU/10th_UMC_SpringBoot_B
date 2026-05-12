package com.example.chap04.domain.mission.service;

import com.example.chap04.domain.mission.dto.MissionResponseDTO;
import com.example.chap04.domain.mission.entity.MemberMission;
import com.example.chap04.domain.mission.entity.MemberMissionStatus;
import com.example.chap04.domain.mission.entity.Mission;
import com.example.chap04.domain.mission.repository.MemberMissionRepository;
import com.example.chap04.domain.mission.repository.MissionRepository;
import com.example.chap04.global.common.paging.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.temporal.ChronoUnit;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    public PageResponse<MissionResponseDTO.MyMissionResponseDto> getMyMission(
            String type,
            int page,
            int size
    ) {
        MemberMissionStatus status = switch (type) {
            case "inProgress" -> MemberMissionStatus.IN_PROGRESS;
            case "completed" -> MemberMissionStatus.COMPLETED;
            default -> throw new IllegalArgumentException("type은 inProgress 또는 completed만 가능합니다.");
        };

        Pageable pageable = PageRequest.of(page, size);

        Page<MemberMission> memberMissionPage =
                memberMissionRepository.findMyMissions(status, pageable);

        List<MissionResponseDTO.MyMissionResponseDto> missions =
                memberMissionPage.getContent().stream()
                        .map(memberMission -> MissionResponseDTO.MyMissionResponseDto.builder()
                                .point(memberMission.getMission().getPoint())
                                .storeName(memberMission.getMission().getStore().getName())
                                .conditional(memberMission.getMission().getConditional())
                                .isComplete(memberMission.getStatus() == MemberMissionStatus.COMPLETED)
                                .build())
                        .toList();

        return PageResponse.of(missions, memberMissionPage);
    }

    public PageResponse<MissionResponseDTO.AvailableMissionResponseDto> getAvailableMissions(
            Long locationId,
            int page,
            int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        LocalDate today = LocalDate.now();

        Page<Mission> missionPage =
                missionRepository.findAvailableMissionsByLocation(locationId, today, pageable);

        List<MissionResponseDTO.AvailableMissionResponseDto> missions =
                missionPage.getContent().stream()
                        .map(mission -> MissionResponseDTO.AvailableMissionResponseDto.builder()
                                .missionId(mission.getId())
                                .storeName(mission.getStore().getName())
                                .category(mission.getStore().getCategory())
                                .conditional(mission.getConditional())
                                .point(mission.getPoint())
                                .deadline(mission.getDeadline())
                                .dDay(toDDay(mission.getDeadline()))
                                .build())
                        .toList();

        return PageResponse.of(missions, missionPage);
    }

    private String toDDay(LocalDate deadline) {
        long days = ChronoUnit.DAYS.between(LocalDate.now(), deadline);

        if (days == 0) {
            return "D-Day";
        }

        return "D-" + days;
    }

    //    public MissionResponseDTO.AchievedCountResponse getAchievedCount(Long memberId) {
//    }
//
//
//    public void createMissionSuccess(Long memberId) {
//
//    }
}
