package com.example.umc10thsb.domain.member.controller;

import com.example.umc10thsb.domain.member.dto.MemberReqDTO;
import com.example.umc10thsb.domain.member.dto.MemberResDTO;
import com.example.umc10thsb.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10thsb.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Member", description = "회원 / 마이페이지 API")
@RestController
@RequestMapping("/api")
public class MemberController {

    // 회원가입
    @Operation(summary = "회원가입", description = "신규 회원을 등록한다.")
    @PostMapping("/members/sign-up")
    public ApiResponse<MemberResDTO.SignUp> signUp(
            @Valid @RequestBody MemberReqDTO.SignUp request
    ) {
        MemberResDTO.SignUp response = MemberResDTO.SignUp.builder()
                .memberId(1L)
                .email(request.email())
                .name(request.name())
                .build();

        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_SIGNUP_SUCCESS, response);
    }

    // 마이페이지 조회
    @Operation(summary = "마이페이지 조회", description = "내 마이페이지 정보를 조회한다.")
    @GetMapping("/mypage/me")
    public ApiResponse<MemberResDTO.GetInfo> getMyPage() {
        MemberResDTO.GetInfo response = MemberResDTO.GetInfo.builder()
                .name("엄민서")
                .nickname("mango")
                .profileUrl("https://example.com/profile.jpg")
                .email("mango@example.com")
                .phoneNumber("010-1234-5678")
                .point(1500)
                .build();

        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_INFO_SUCCESS, response);
    }

    // 마이페이지 수정
    @Operation(summary = "마이페이지 수정", description = "내 마이페이지 정보를 수정한다.")
    @PatchMapping("/mypage/me")
    public ApiResponse<MemberResDTO.GetInfo> updateMyPage(
            @Valid @RequestBody MemberReqDTO.UpdateInfo request
    ) {
        MemberResDTO.GetInfo response = MemberResDTO.GetInfo.builder()
                .name(request.name() != null ? request.name() : "엄민서")
                .nickname(request.nickname() != null ? request.nickname() : "mango")
                .profileUrl(request.profileUrl() != null ? request.profileUrl()
                                                         : "https://example.com/profile.jpg")
                .email("mango@example.com")
                .phoneNumber(request.phoneNumber() != null ? request.phoneNumber()
                                                           : "010-1234-5678")
                .point(1500)
                .build();

        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_UPDATE_SUCCESS, response);
    }
}
