package com.example.umc10thsb.domain.mission.controller;

import com.example.umc10thsb.domain.mission.dto.MissionResDTO;
import com.example.umc10thsb.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10thsb.domain.mission.service.MissionService;
import com.example.umc10thsb.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Mission", description = "미션 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    // 내 미션 목록 조회 (진행중/완료 토글, 페이지네이션)
    @Operation(summary = "내 미션 목록 조회",
            description = "로그인 한 사용자의 미션 목록을 페이지네이션으로 조회한다. status=CHALLENGING/COMPLETED 로 필터링 가능.")
    @GetMapping("/me")
    public ApiResponse<MissionResDTO.MissionList> getMyMissions(
            @RequestParam(defaultValue = "1") Long memberId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String status
    ) {
        MissionResDTO.MissionList response = missionService.getMyMissions(memberId, status, page, size);
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_SUCCESS, response);
    }

    // 홈 화면 - 동네별 미션 진행률
    @Operation(summary = "홈 화면 미션 진행률",
            description = "선택된 동네(address) 기준으로 내 미션 진행률(완료/전체)과 진행중 미션 카드 리스트를 조회한다.")
    @GetMapping("/home")
    public ApiResponse<MissionResDTO.HomeMissions> getHomeMissions(
            @RequestParam(defaultValue = "1") Long memberId,
            @RequestParam(required = false) String address
    ) {
        MissionResDTO.HomeMissions response = missionService.getHomeMissions(memberId, address);
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_HOME_SUCCESS, response);
    }

    // 미션 상세 조회
    @Operation(summary = "미션 상세 조회", description = "missionId 로 미션 상세 정보를 조회한다.")
    @GetMapping("/me/{missionId}")
    public ApiResponse<MissionResDTO.MissionDetail> getMissionDetail(
            @RequestParam(defaultValue = "1") Long memberId,
            @PathVariable Long missionId
    ) {
        MissionResDTO.MissionDetail response = missionService.getMissionDetail(memberId, missionId);
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_DETAIL_SUCCESS, response);
    }

    // 미션 완료 처리
    @Operation(summary = "미션 완료 처리", description = "진행중 미션을 완료 상태로 변경하고 보상 포인트를 적립한다.")
    @PatchMapping("/me/{missionId}/complete")
    public ApiResponse<MissionResDTO.CompleteMission> completeMission(
            @RequestParam(defaultValue = "1") Long memberId,
            @PathVariable Long missionId
    ) {
        MissionResDTO.CompleteMission response = missionService.completeMission(memberId, missionId);
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETE_SUCCESS, response);
    }
}
