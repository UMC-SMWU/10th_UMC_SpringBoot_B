package com.example._th.domain.mission.controller;

import com.example._th.global.apiPayload.ApiResponse;
import com.example._th.global.apiPayload.code.status.SuccessStatus;
import com.example._th.domain.mission.dto.MissionReqDTO;
import com.example._th.domain.mission.dto.MissionResDTO;
import com.example._th.domain.mission.service.MissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/me/missions")
public class MissionController {

    private final MissionService missionService;

    // 미션 1 & 3: 내가 진행중인 미션 목록 조회 (오프셋 기반 페이징)
    // 조건: 사용자 ID를 Request Body에서 받음, 검증 어노테이션(@Valid) 적용
    @GetMapping("")
    public ApiResponse<MissionResDTO.MissionPreViewListDTO> getMissionList(
            @Valid @RequestBody MissionReqDTO.GetListDTO request,
            @RequestParam(name = "page") Integer page
    ) {
        // 서비스 호출 시 DTO와 페이지 번호를 전달합니다.
        // page - 1 처리는 서비스 계층에서 하거나 여기서 할 수 있습니다 (보통 프론트가 1부터 주면 서버는 0부터 처리)
        return ApiResponse.onSuccess(
                SuccessStatus.MISSION_LIST_OK,
                missionService.getMissionList(request, page)
        );
    }

    // 미션 성공 누르기 (기존 코드 유지)
    @PatchMapping("/{missionId}/complete")
    public ApiResponse<MissionResDTO.MissionCompleteResultDTO> completeMission(
            @PathVariable(name = "missionId") Long missionId
    ) {
        return ApiResponse.onSuccess(
                SuccessStatus.MISSION_COMPLETE_OK,
                missionService.completeMission(missionId)
        );
    }
}