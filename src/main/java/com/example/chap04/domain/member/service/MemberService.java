package com.example.chap04.domain.member.service;

import com.example.chap04.domain.location.dto.LocationResponse;
import com.example.chap04.domain.mission.dto.MissionResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    public MissionResponseDTO.AchievedCountResponse getAchievedCount(Long memberId) {

    }

    public MissionResponseDTO.MyMissionListResponseDto getMyMission(Long memberId) {
        return null;
    }

    public LocationResponse.MyLocation getLocations(Long memberId) {
    }
}
