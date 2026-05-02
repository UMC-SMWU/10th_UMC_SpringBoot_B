package com.example.chap04.domain.mission.controller;

import com.example.chap04.domain.member.service.MemberService;
import com.example.chap04.domain.mission.dto.MissionRequestDTO;
import com.example.chap04.domain.mission.dto.MissionResponseDTO;
import com.example.chap04.global.ApiResponse;
import com.example.chap04.global.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {
    private final MemberService memberService;

    @GetMapping("/achievedCount/{memberId}")
    public ResponseEntity<ApiResponse<MissionResponseDTO.AchievedCountResponse>> getAchievedCountRequestDTO(
            @PathVariable Long memberId
    ) {
        MissionResponseDTO.AchievedCountResponse result = memberService.getAchievedCount(memberId);
        return ResponseEntity
                .status(GeneralSuccessCode.GET_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.GET_SUCCESS,result));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResponse<MissionResponseDTO.MyMissionListResponseDto>> getMyMissionRequestDTO (
            @PathVariable Long memberId
    ){
        MissionResponseDTO.MyMissionListResponseDto result = memberService.getMyMission(memberId);
        return ResponseEntity
                .status(GeneralSuccessCode.GET_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.GET_SUCCESS, result));
    }
}
