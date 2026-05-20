package com.example._th.global.security.service;


import com.example._th.domain.member.entity.Member;
import com.example._th.domain.member.exception.MemberException;
import com.example._th.domain.member.exception.code.MemberErrorCode;
import com.example._th.domain.member.repository.MemberRepository;
import com.example._th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return new AuthMember(member);
    }
}