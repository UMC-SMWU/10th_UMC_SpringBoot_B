package com.example.umc10thsb.domain.member.converter;

import com.example.umc10thsb.domain.member.dto.MemberReqDTO;
import com.example.umc10thsb.domain.member.dto.MemberResDTO;
import com.example.umc10thsb.domain.member.entity.Food;
import com.example.umc10thsb.domain.member.entity.Member;
import com.example.umc10thsb.domain.member.entity.Term;
import com.example.umc10thsb.domain.member.entity.mapping.MemberFood;
import com.example.umc10thsb.domain.member.entity.mapping.MemberTerm;
import com.example.umc10thsb.domain.member.enums.Gender;
import com.example.umc10thsb.domain.member.enums.SocialType;
import com.example.umc10thsb.global.security.dto.OAuthDTO;

import java.util.List;

public class MemberConverter {

    private MemberConverter() {}

    // 회원가입 요청 DTO + 사전 검증된 음식/약관 → Member 엔티티
    public static Member toMember(MemberReqDTO.SignUp req,
                                  String encodedPassword,
                                  List<Food> foods,
                                  List<Term> terms,
                                  List<Long> agreedTermIds) {
        Gender gender = (req.gender() != null && !req.gender().isBlank())
                ? Gender.valueOf(req.gender().toUpperCase())
                : null;

        Member member = Member.builder()
                .name(req.name())
                .email(req.email())
                .password(encodedPassword)
                .phoneNumber(req.phoneNumber())
                .gender(gender)
                .socialType(SocialType.LOCAL)
                .build();

        // 선호 음식 매핑 추가
        if (foods != null) {
            for (Food food : foods) {
                MemberFood mf = MemberFood.builder()
                        .member(member)
                        .food(food)
                        .build();
                member.getMemberFoods().add(mf);
            }
        }

        // 약관 동의 매핑 추가
        if (terms != null) {
            for (Term term : terms) {
                boolean agreed = agreedTermIds != null && agreedTermIds.contains(term.getId());
                MemberTerm mt = MemberTerm.builder()
                        .member(member)
                        .term(term)
                        .agreed(agreed)
                        .build();
                member.getMemberTerms().add(mt);
            }
        }

        return member;
    }

    public static MemberResDTO.SignUp toSignUpRes(Member member,
                                                  String accessToken,
                                                  String tokenType,
                                                  long expiresIn) {
        return MemberResDTO.SignUp.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .accessToken(accessToken)
                .tokenType(tokenType)
                .expiresIn(expiresIn)
                .build();
    }

    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .name(member.getName())
                .nickname(member.getNickname())
                .profileUrl(member.getProfileUrl())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }

    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .name(dto.getName())
                .email(dto.getSocialEmail())
                .socialType(dto.getSocialType())
                .socialUid(dto.getSocialUid())
                .build();
    }

    public static MemberResDTO.Login toLogin(String accessToken) {
        return MemberResDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }
}
