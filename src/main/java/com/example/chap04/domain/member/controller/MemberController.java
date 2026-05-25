package com.example.chap04.domain.member.controller;

import com.example.chap04.domain.member.dto.MemberResponseDTO;
import com.example.chap04.domain.member.service.MemberService;
import com.example.chap04.global.api.ApiResponse;
import com.example.chap04.global.api.GeneralSuccessCode;
import com.example.chap04.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<MemberResponseDTO.MyPageResponseDto>> getInfo(
            @AuthenticationPrincipal AuthMember member
            ) {
        MemberResponseDTO.MyPageResponseDto result = memberService.getInfo(member);

        return ApiResponse.onSuccessResponse(GeneralSuccessCode.GET_SUCCESS, result);
    }
}