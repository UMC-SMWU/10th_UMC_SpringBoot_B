package com.example.chap04.domain.member.controller;

import com.example.chap04.domain.member.service.MemberService;
import com.example.chap04.domain.mission.dto.MissionResponseDTO;
import com.example.chap04.global.ApiResponse;
import com.example.chap04.global.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{memberId}/missions")
    public ResponseEntity<ApiResponse<MissionResponseDTO.MyMissionListResponseDto>> getMyMission (
            @PathVariable Long memberId
    ){
        MissionResponseDTO.MyMissionListResponseDto result = memberService.getMyMission(memberId);
        return ApiResponse.onSuccessResponse(GeneralSuccessCode.GET_SUCCESS,result);
    }
}
