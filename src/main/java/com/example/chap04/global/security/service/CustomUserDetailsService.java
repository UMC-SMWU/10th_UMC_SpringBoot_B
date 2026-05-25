package com.example.chap04.global.security.service;

import com.example.chap04.domain.member.entity.Member;
import com.example.chap04.domain.member.repository.MemberRepository;
import com.example.chap04.global.api.GeneralErrorCode;
import com.example.chap04.global.api.ProjectException;
import com.example.chap04.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 로그인 요청 들어오면, DB에서 사용자 정보 가져오는 클래스
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 일반 로그인: 이메일로 유저 조회
    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.MEMBER_NOT_FOUND));
        return new AuthMember(member);
    }

    // OAuth 로그인: socialType + uid로 유저 조회
    public UserDetails loadUserByUidAndSocialType(Member.SocialType socialType, String uid) {
        Member member = memberRepository.findBySocialTypeAndSocialUid(socialType, uid)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.MEMBER_NOT_FOUND));
        return new AuthMember(member);
    }
}
