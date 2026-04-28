package com.example._th.domain.mission.controller;
import com.example._th.domain.mission.dto.MissionResDTO;
import com.example._th.domain.mission.service.MissionService;
import com.example._th.domain.mission.status.MissionSuccessCode;
import com.example._th.global.apiPayload.ApiResponse;
import com.example._th.global.apiPayload.code.BaseSuccessCode; // 이거 없으면 BaseSuccessCode에 빨간줄!
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/me/missions")
public class MissionController {

    private final MissionService missionService;

    // 3. 미션 목록 조회 (Query Parameter: ?status=CHALLENGING)
    @GetMapping("")
    public ApiResponse<MissionResDTO.MissionPreViewListDTO> getMissionList(
            @RequestParam(name = "status") String status
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_LIST_OK;
        return ApiResponse.onSuccess(code, missionService.getMissionList(status));
    }

    // 4. 미션 성공 누르기 (Path Variable: {missionId})
    @PatchMapping("/{missionId}/complete")
    public ApiResponse<MissionResDTO.MissionCompleteResultDTO> completeMission(
            @PathVariable(name = "missionId") Long missionId
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_COMPLETE_OK;
        return ApiResponse.onSuccess(code, missionService.completeMission(missionId));
    }
}