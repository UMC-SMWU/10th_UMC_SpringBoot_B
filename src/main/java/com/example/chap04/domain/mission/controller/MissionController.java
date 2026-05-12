package com.example.chap04.domain.mission.controller;

import com.example.chap04.domain.mission.dto.MemberMissionResponseDTO;
import com.example.chap04.domain.mission.service.MissionService;
import com.example.chap04.global.api.ApiResponse;
import com.example.chap04.global.api.GeneralSuccessCode;
import com.example.chap04.global.common.paging.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<MemberMissionResponseDTO.MyMissionResponseDto>>> getMyMission(
            @RequestParam String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<MemberMissionResponseDTO.MyMissionResponseDto> result =
                missionService.getMyMission(type, page, size);

        return ApiResponse.onSuccessResponse(GeneralSuccessCode.GET_SUCCESS, result);
    }

    @GetMapping("/available")
    public ResponseEntity<ApiResponse<PageResponse<MemberMissionResponseDTO.AvailableMissionResponseDto>>> getAvailableMissions(
            @RequestParam Long locationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<MemberMissionResponseDTO.AvailableMissionResponseDto> result =
                missionService.getAvailableMissions(locationId, page, size);

        return ApiResponse.onSuccessResponse(GeneralSuccessCode.GET_SUCCESS, result);
    }


//    @GetMapping("/achievedCount/{memberId}")
//    public ResponseEntity<ApiResponse<MissionResponseDTO.AchievedCountResponse>> getAchievedCount(
//            @PathVariable Long memberId
//    ) {
//        MissionResponseDTO.AchievedCountResponse result = missionService.getAchievedCount(memberId);
//        return ApiResponse.onSuccessResponse(GeneralSuccessCode.GET_SUCCESS,result);
//    }
//
//    @GetMapping("")
//    public ResponseEntity<ApiResponse<MissionResponseDTO.missionItemsListDto>> getMissionItems (
//            @RequestParam MissionStateType type
//    ) {
//        MissionResponseDTO.missionItemsListDto result = missionService.getMissionItems(type);
//        return ApiResponse.onSuccessResponse(GeneralSuccessCode.GET_SUCCESS, result);
//    }
//
//    @PostMapping("/{missionId}/success")
//    public ResponseEntity<ApiResponse<Void>> createMissionSuccess (
//            @PathVariable Long memberId
//    ) {
//        missionService.createMissionSuccess(memberId);
//        return ApiResponse.onSuccessResponse(GeneralSuccessCode.POST_SUCCESS, null);
//    }
}
