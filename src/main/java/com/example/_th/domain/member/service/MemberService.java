package com.example._th.domain.member.service;

import com.example._th.domain.member.entity.Member;
import com.example._th.domain.member.exception.MemberException;
import com.example._th.domain.member.exception.code.MemberErrorCode;
import com.example._th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member getMyPageInfo(Long memberId) {
        // ID로 회원을 찾고, 없으면 에러를 던집니다.
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}