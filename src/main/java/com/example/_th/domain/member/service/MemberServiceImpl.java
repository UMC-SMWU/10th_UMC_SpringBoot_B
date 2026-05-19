package com.example._th.domain.member.service;

import com.example._th.domain.member.dto.HomeResDTO;
import org.springframework.stereotype.Service; // 이거 추가
import org.springframework.transaction.annotation.Transactional; // 이거 추가

@Service
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
    @Override
    public HomeResDTO.HomeViewDTO getHome() {
        return null;
    }
}