package com.example.chap04.domain.mission.service;

import com.example.chap04.domain.mission.dto.MissionResponseDTO;
import com.example.chap04.domain.mission.enums.MissionStateType;
import org.springframework.stereotype.Service;

@Service
public class MissionService {
    public MissionResponseDTO.AchievedCountResponse getAchievedCount(Long memberId) {
    }

    public MissionResponseDTO.missionItemsListDto getMissionItems(MissionStateType type) {
        return null;
    }

    public void createMissionSuccess(Long memberId) {

    }
}
