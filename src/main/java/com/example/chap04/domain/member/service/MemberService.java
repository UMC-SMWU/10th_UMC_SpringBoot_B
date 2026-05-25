package com.example.chap04.domain.member.service;

import com.example.chap04.domain.member.converter.MemberConverter;
import com.example.chap04.domain.member.dto.MemberResponseDTO;
import com.example.chap04.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    public MemberResponseDTO.MyPageResponseDto getInfo(AuthMember member) {

        return MemberConverter.toGetInfo(member.getMember());
    }
}
