package com.example.umc10thsb.domain.member.service;

import com.example.umc10thsb.domain.member.converter.MemberConverter;
import com.example.umc10thsb.domain.member.dto.MemberReqDTO;
import com.example.umc10thsb.domain.member.dto.MemberResDTO;
import com.example.umc10thsb.domain.member.entity.Food;
import com.example.umc10thsb.domain.member.entity.Member;
import com.example.umc10thsb.domain.member.entity.Term;
import com.example.umc10thsb.domain.member.exception.MemberException;
import com.example.umc10thsb.domain.member.exception.code.MemberErrorCode;
import com.example.umc10thsb.domain.member.repository.FoodRepository;
import com.example.umc10thsb.domain.member.repository.MemberRepository;
import com.example.umc10thsb.domain.member.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final TermRepository termRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public MemberResDTO.SignUp signUp(MemberReqDTO.SignUp req) {
        // 이메일 중복 체크
        if (memberRepository.existsByEmail(req.email())) {
            throw new MemberException(MemberErrorCode.DUPLICATE_EMAIL);
        }

        // 선호 음식 검증
        List<Food> foods = List.of();
        if (req.preferredFoodIds() != null && !req.preferredFoodIds().isEmpty()) {
            foods = foodRepository.findAllByIdIn(req.preferredFoodIds());
            if (foods.size() != req.preferredFoodIds().size()) {
                throw new MemberException(MemberErrorCode.FOOD_NOT_FOUND);
            }
        }

        // 약관 검증 (전체 약관 로드 → 동의/비동의 매핑 생성)
        List<Term> terms = List.of();
        if (req.agreedTermIds() != null && !req.agreedTermIds().isEmpty()) {
            terms = termRepository.findAllByIdIn(req.agreedTermIds());
            if (terms.size() != req.agreedTermIds().size()) {
                throw new MemberException(MemberErrorCode.TERM_NOT_FOUND);
            }
        }

        String encodedPassword = passwordEncoder.encode(req.password());

        Member member = MemberConverter.toMember(req, encodedPassword, foods, terms, req.agreedTermIds());

        Member saved = memberRepository.save(member);
        return MemberConverter.toSignUpRes(saved);
    }

    // 마이페이지 조회
    public MemberResDTO.GetInfo getMyPage(Long memberId) {
        Member member = findMemberById(memberId);
        return MemberConverter.toGetInfo(member);
    }

    // 마이페이지 수정
    @Transactional
    public MemberResDTO.GetInfo updateMyPage(Long memberId, MemberReqDTO.UpdateInfo req) {
        Member member = findMemberById(memberId);
        member.updateProfile(req.name(), req.nickname(), req.profileUrl(), req.phoneNumber());
        return MemberConverter.toGetInfo(member);
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
