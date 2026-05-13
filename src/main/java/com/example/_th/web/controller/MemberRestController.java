package com.example._th.web.controller;


import com.example._th.domain.member.service.MemberService;
import com.example._th.domain.mission.entity.mapping.MemberMission;
import com.example._th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberService memberService; // @RequiredArgsConstructor로 자동 주입됨

    @GetMapping("/{memberId}/missions")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API", description = "진행 중/완료 미션을 페이징해서 조회합니다.")
    public ApiResponse<Page<MemberMission>> getMyMissionList(
            @PathVariable Long memberId,
            @RequestParam MissionStatus status,
            @RequestParam Integer page) {

        Page<MemberMission> missionPage = memberService.getMyMissionList(memberId, status, page);
        return ApiResponse.onSuccess(missionPage);
    }
}