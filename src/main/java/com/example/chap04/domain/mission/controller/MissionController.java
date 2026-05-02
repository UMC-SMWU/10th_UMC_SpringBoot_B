package com.example.chap04.domain.mission.controller;

import com.example.chap04.domain.member.service.MemberService;
import com.example.chap04.domain.mission.dto.MissionResponseDTO;
import com.example.chap04.domain.mission.enums.MissionStateType;
import com.example.chap04.domain.mission.service.MissionService;
import com.example.chap04.global.ApiResponse;
import com.example.chap04.global.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/achievedCount/{memberId}")
    public ResponseEntity<ApiResponse<MissionResponseDTO.AchievedCountResponse>> getAchievedCount(
            @PathVariable Long memberId
    ) {
        MissionResponseDTO.AchievedCountResponse result = missionService.getAchievedCount(memberId);
        return ApiResponse.onSuccessResponse(GeneralSuccessCode.GET_SUCCESS,result);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<MissionResponseDTO.missionItemsListDto>> getMissionItems (
            @RequestParam MissionStateType type
    ) {
        MissionResponseDTO.missionItemsListDto result = missionService.getMissionItems(type);
        return ApiResponse.onSuccessResponse(GeneralSuccessCode.GET_SUCCESS, result);
    }

    @PostMapping("/{missionId}/success")
    public ResponseEntity<ApiResponse<Void>> createMissionSuccess (
            @PathVariable Long memberId
    ) {
        missionService.createMissionSuccess(memberId);
        return ApiResponse.onSuccessResponse(GeneralSuccessCode.POST_SUCCESS, null);
    }
}
