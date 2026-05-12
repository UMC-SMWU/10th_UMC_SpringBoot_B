package com.example.chap04.domain.member.controller;

import com.example.chap04.domain.member.dto.MemberResponseDTO;
import com.example.chap04.domain.member.service.MemberService;
import com.example.chap04.global.api.ApiResponse;
import com.example.chap04.global.api.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}/my-page")
    public ResponseEntity<ApiResponse<MemberResponseDTO.MyPageResponseDto>> getMyPage(
            @PathVariable Long memberId
    ) {
        MemberResponseDTO.MyPageResponseDto result = memberService.getMyPage(memberId);

        return ApiResponse.onSuccessResponse(GeneralSuccessCode.GET_SUCCESS, result);
    }
}