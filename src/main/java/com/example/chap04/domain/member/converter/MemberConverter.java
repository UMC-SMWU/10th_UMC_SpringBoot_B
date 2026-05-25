package com.example.chap04.domain.member.converter;

import com.example.chap04.domain.member.dto.MemberResponseDTO;
import com.example.chap04.domain.member.entity.Member;
import com.example.chap04.global.security.dto.OAuthDTO;

import java.time.LocalDate;

public class MemberConverter {

    // 1. OAuth 로그인
    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .name(dto.getName())
                .nickname(dto.getName())
                .email(dto.getEmail())
                .password("OAUTH_USER")

                .loginType(Member.LoginType.OAUTH)

                .socialType(dto.getSocialType())
                .socialUid(dto.getSocialUid())

                .gender(Member.Gender.NONE)
                .birth(LocalDate.now())
                .address(Member.Address.GANGNAM_GU)
                .detailAddress("")
                .point(0)
                .phoneNumber(null)
                .build();
    }

    public static MemberResponseDTO.Login toLogin(String accessToken) {
        return MemberResponseDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }

    public static MemberResponseDTO.MyPageResponseDto toGetInfo(Member member) {
        return MemberResponseDTO.MyPageResponseDto.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .isPhoneVerified(false)
                .point(member.getPoint())
                .build();
    }
}

// 2. 일반로그인
//public class MemberConverter {
//
//    public static MemberResponseDTO.MyPageResponseDto toGetInfo(Member member) {
//        return MemberResponseDTO.MyPageResponseDto.builder()
//                .memberId(member.getId())
//                .nickname(member.getNickname())
//                .email(member.getEmail())
//                .phoneNumber(member.getPhoneNumber())
//                .isPhoneVerified(false)
//                .point(member.getPoint())
//                .build();
//    }
//}
