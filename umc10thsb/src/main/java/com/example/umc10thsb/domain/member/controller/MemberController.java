package com.example.umc10thsb.domain.member.controller;

import com.example.umc10thsb.domain.member.dto.MemberReqDTO;
import com.example.umc10thsb.domain.member.dto.MemberResDTO;
import com.example.umc10thsb.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10thsb.domain.member.service.MemberService;
import com.example.umc10thsb.global.apiPayload.ApiResponse;
import com.example.umc10thsb.global.security.entity.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Member", description = "회원 / 마이페이지 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @Operation(summary = "회원가입", description = "신규 회원을 등록한다.")
    @PostMapping("/members/sign-up")
    public ApiResponse<MemberResDTO.SignUp> signUp(
            @Valid @RequestBody MemberReqDTO.SignUp request
    ) {
        MemberResDTO.SignUp response = memberService.signUp(request);
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_SIGNUP_SUCCESS, response);
    }

    // 마이페이지 조회
    @Operation(summary = "마이페이지 조회", description = "토큰의 인증 회원 정보로 마이페이지를 조회한다.")
    @GetMapping("/v2/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @AuthenticationPrincipal AuthMember member
    ) {
        MemberResDTO.GetInfo response = memberService.getInfo(member);
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_INFO_SUCCESS, response);
    }

    // 마이페이지 수정
    @Operation(summary = "마이페이지 수정", description = "토큰의 인증 회원 정보로 마이페이지를 수정한다.")
    @PatchMapping("/v2/users/me")
    public ApiResponse<MemberResDTO.GetInfo> updateInfo(
            @AuthenticationPrincipal AuthMember member,
            @Valid @RequestBody MemberReqDTO.UpdateInfo request
    ) {
        MemberResDTO.GetInfo response = memberService.updateInfo(member, request);
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_UPDATE_SUCCESS, response);
    }
}
