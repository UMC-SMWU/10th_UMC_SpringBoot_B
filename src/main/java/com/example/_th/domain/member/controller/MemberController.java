package com.example._th.domain.member.controller;
import com.example._th.domain.member.dto.MemberReqDTO;
import com.example._th.domain.member.dto.MemberResDTO;
import com.example._th.domain.member.status.MemberSuccessCode;
import com.example._th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.JoinResultDTO> join(
            @RequestBody MemberReqDTO.JoinDTO request
    ) {

        return ApiResponse.onSuccess(MemberSuccessCode.JOIN_OK, memberService.joinMember(request));
    }
}