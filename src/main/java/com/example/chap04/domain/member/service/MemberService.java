package com.example.chap04.domain.member.service;

import com.example.chap04.domain.member.dto.MemberResponseDTO;
import com.example.chap04.domain.member.entity.Member;
import com.example.chap04.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponseDTO.MyPageResponseDto getMyPage(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다. memberId = " + memberId));

        return MemberResponseDTO.MyPageResponseDto.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .isPhoneVerified(false)
                .point(member.getPoint())
                .build();
    }

    //    public MissionResponseDTO.AchievedCountResponse getAchievedCount(Long memberId) {
//
//    }

//    public LocationResponse.MyLocation getLocations(Long memberId) {
//    }
}
