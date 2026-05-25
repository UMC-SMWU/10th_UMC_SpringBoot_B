package com.example.chap04.domain.auth.service;

import com.example.chap04.domain.auth.dto.AuthRequestDTO;
import com.example.chap04.domain.member.entity.Member;
import com.example.chap04.domain.member.repository.MemberRepository;
import com.example.chap04.global.api.GeneralErrorCode;
import com.example.chap04.global.api.ProjectException;
import com.example.chap04.global.security.entity.AuthMember;
import com.example.chap04.global.security.filter.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void signin(AuthRequestDTO.signin request) {

        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new ProjectException(GeneralErrorCode.DUPLICATED_EMAIL);
        }

        String encryptedPassword = passwordEncoder.encode(request.getPassword());

        Member member = Member.builder()
                .name(request.getName())
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(encryptedPassword)
                .birth(request.getBirth())
                .gender(request.getGender())
                .address(request.getAddress())
                .detailAddress(request.getDetailAddress())
                .phoneNumber(request.getPhoneNumber())
                .point(0)
                .build();

        memberRepository.save(member);
    }

    public String login(AuthRequestDTO.login request) {
        // 이메일로 멤버 조회
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.MEMBER_NOT_FOUND));

        // 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new ProjectException(GeneralErrorCode.UNAUTHORIZED);
        }

        // JWT AccessToken 생성
        AuthMember authMember = new AuthMember(member);
        return jwtUtil.createAccessToken(authMember);
    }
}