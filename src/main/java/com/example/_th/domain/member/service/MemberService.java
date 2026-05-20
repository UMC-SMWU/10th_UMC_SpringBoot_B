package com.example._th.domain.member.service;

import com.example._th.domain.member.dto.MemberReqDTO;
import com.example._th.domain.member.dto.MemberResDTO;
import com.example._th.domain.member.entity.Member;
import com.example._th.domain.member.exception.MemberException;
import com.example._th.domain.member.exception.code.MemberErrorCode;
import com.example._th.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository; // DB 금고 열쇠
    private final PasswordEncoder passwordEncoder;   // 1단계에서 등록한 암호화 기계 자동 주입!

    @Transactional
    public MemberResDTO.JoinResultDTO joinMember(MemberReqDTO.@org.jetbrains.annotations.UnknownNullability JoinDTO request) {

        // 1. 가방에서 사용자가 입력한 원래 생글씨 비밀번호(예: "test")를 꺼냅니다.
        String rawPassword = request.getPassword();

        // 2. ⚡[3단계 핵심] 기계에 넣어서 알아볼 수 없는 외계어로 솔트 처리(암호화) 합니다!
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // 3. 외계어로 꽁꽁 숨겨진 비밀번호를 넣어서 진짜 우리 DB 테이블 모양(Entity)으로 조립합니다.
        // (💡 주의: 만약 본인 Member 엔티티에 성별, 주소 필드가 코드로 선언되어 있어야 정상 작동합니다.)
        Member newMember = Member.builder()
                .email(request.getEmail())
                .password(encodedPassword) // 💡 생글씨 비밀번호 대신 암호화된 외계어 비밀번호를 골인!
                .name(request.getName())
                .gender(request.getGender())
                .birth(request.getBirth())
                .address(request.getAddress())
                .detailAddress(request.getDetailAddress())
                .build();

        // 4. DB 금고에 소중하게 저장 완료!
        memberRepository.save(newMember);
        return null;
    }

    public Member getMyPageInfo(Long memberId) {
        // ID로 회원을 찾고, 없으면 에러를 던집니다.
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}