package com.example.umc10thsb.domain.mission.controller;

import com.example.umc10thsb.domain.mission.dto.MissionResDTO;
import com.example.umc10thsb.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10thsb.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Mission", description = "미션 관련 API")
@RestController
@RequestMapping("/api/missions")
public class MissionController {

    // 내 미션 목록 조회
    @Operation(summary = "내 미션 목록 조회",
            description = "로그인 한 사용자의 미션 목록을 페이지네이션으로 조회한다.")
    @GetMapping("/me")
    public ApiResponse<MissionResDTO.MissionList> getMyMissions(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String status
    ) {
        String missionStatus = (status != null) ? status : "ALL";

        // 더미 데이터
        List<MissionResDTO.MissionItem> missions = List.of(
                MissionResDTO.MissionItem.builder()
                        .missionId(1L)
                        .title("아메리카노 한 잔 구매")
                        .content("스타벅스 매장에서 아메리카노 한 잔을 주문하세요.")
                        .reward(100)
                        .status(missionStatus)
                        .build()
        );

        MissionResDTO.MissionList response = MissionResDTO.MissionList.builder()
                .missions(missions)
                .page(page)
                .size(size)
                .totalPages(1)
                .totalElements(missions.size())
                .build();

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_SUCCESS, response);
    }

    // 미션 상세 조회
    @Operation(summary = "미션 상세 조회", description = "missionId로 미션 상세 정보를 조회한다.")
    @GetMapping("/me/{missionId}")
    public ApiResponse<MissionResDTO.MissionDetail> getMissionDetail(
            @PathVariable Long missionId
    ) {
        MissionResDTO.MissionDetail mission = MissionResDTO.MissionDetail.builder()
                .missionId(missionId)
                .title("아메리카노 한 잔 구매")
                .content("스타벅스 매장에서 아메리카노 한 잔을 주문하세요.")
                .reward(100)
                .status("IN_PROGRESS")
                .createdAt("2026-04-29T10:00:00")
                .deadline("2026-05-29")
                .build();

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_DETAIL_SUCCESS, mission);
    }

    // 미션 완료 처리
    @Operation(summary = "미션 완료 처리", description = "진행중 미션을 완료 상태로 변경한다.")
    @PatchMapping("/me/{missionId}/complete")
    public ApiResponse<MissionResDTO.CompleteMission> completeMission(
            @PathVariable Long missionId
    ) {
        MissionResDTO.CompleteMission response = MissionResDTO.CompleteMission.builder()
                .missionId(missionId)
                .status("COMPLETED")
                .build();

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETE_SUCCESS, response);
    }
}
