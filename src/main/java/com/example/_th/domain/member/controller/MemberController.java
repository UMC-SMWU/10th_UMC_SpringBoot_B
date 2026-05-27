package com.example._th.domain.member.controller;

import com.example._th.domain.member.dto.MemberReqDTO;
import com.example._th.domain.member.dto.MemberResDTO;
import com.example._th.domain.member.service.MemberService;
import com.example._th.global.apiPayload.ApiResponse;
import com.example._th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/signup")
    @GetMapping("/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @AuthenticationPrincipal AuthMember member
    ) {
        return ApiResponse.onSuccess(
                memberService.getInfo(member)
        );
    }

}