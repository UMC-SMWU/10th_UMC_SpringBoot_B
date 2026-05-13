package com.example._th.web.controller;

import com.example._th.domain.Mission;
import com.example._th.service.MissionService; // 본인의 미션 서비스 경로
import com.example._th.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionService missionService;

    @GetMapping("/region")
    @Operation(summary = "홈 화면: 지역별 도전 가능 미션 목록 조회 API", description = "특정 지역의 미션 목록을 페이징하여 조회합니다.")
    public ApiResponse<Page<Mission>> getMissionsByRegion(
            @RequestParam String regionName,
            @RequestParam Integer page) {

        Page<Mission> missionPage = missionService.getMissionListByRegion(regionName, page);
        return ApiResponse.onSuccess(missionPage);
    }
}