package com.example.umc10thsb.domain.mission.service;

import com.example.umc10thsb.domain.member.entity.Member;
import com.example.umc10thsb.domain.member.exception.MemberException;
import com.example.umc10thsb.domain.member.exception.code.MemberErrorCode;
import com.example.umc10thsb.domain.member.repository.MemberRepository;
import com.example.umc10thsb.domain.mission.converter.MissionConverter;
import com.example.umc10thsb.domain.mission.dto.MissionReqDTO;
import com.example.umc10thsb.domain.mission.dto.MissionResDTO;
import com.example.umc10thsb.domain.mission.entity.Mission;
import com.example.umc10thsb.domain.mission.entity.mapping.MemberMission;
import com.example.umc10thsb.domain.mission.enums.Address;
import com.example.umc10thsb.domain.mission.enums.MissionStatus;
import com.example.umc10thsb.domain.mission.exception.MissionException;
import com.example.umc10thsb.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10thsb.domain.mission.repository.MemberMissionRepository;
import com.example.umc10thsb.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    // 내가 진행중인 미션 목록
    public MissionResDTO.ChallengingMissionList getMyChallengingMissions(MissionReqDTO.GetChallengingMissions req) {
        // 회원 존재 검증
        if (!memberRepository.existsById(req.memberId())) {
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }

        // 오프셋 기반 페이지네이션
        Pageable pageable = PageRequest.of(req.page() - 1, req.size());

        Page<MemberMission> result = memberMissionRepository
                .findPageByMemberAndStatus(req.memberId(), MissionStatus.CHALLENGING, pageable);

        return MissionConverter.toChallengingMissionList(result);
    }

    // 내 미션 목록 - 진행중/완료 토글, 페이지네이션
    public MissionResDTO.MissionList getMyMissions(Long memberId, String statusParam, int page, int size) {
        // 회원 존재 검증
        if (!memberRepository.existsById(memberId)) {
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }

        Pageable pageable = PageRequest.of(Math.max(page - 1, 0), size);

        Page<MemberMission> result;
        if (statusParam == null || statusParam.isBlank() || "ALL".equalsIgnoreCase(statusParam)) {
            result = memberMissionRepository.findPageByMember(memberId, pageable);
        } else {
            MissionStatus status = parseStatus(statusParam);
            result = memberMissionRepository.findPageByMemberAndStatus(memberId, status, pageable);
        }

        return MissionConverter.toMissionList(result);
    }

    // 미션 상세 - missionId 로 상세 조회
    public MissionResDTO.MissionDetail getMissionDetail(Long memberId, Long missionId) {
        Mission mission = missionRepository.findByIdWithStore(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        MemberMission memberMission = memberMissionRepository
                .findByMemberAndMission(memberId, missionId)
                .orElse(null);

        return MissionConverter.toMissionDetail(mission, memberMission);
    }

    // 미션 완료 처리 - 진행중인 미션을 완료로 전환/회원 포인트에 reward 적립
    @Transactional
    public MissionResDTO.CompleteMission completeMission(Long memberId, Long missionId) {
        MemberMission mm = memberMissionRepository
                .findByMemberAndMission(memberId, missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MEMBER_MISSION_NOT_FOUND));

        if (!mm.isChallenging()) {
            throw new MissionException(MissionErrorCode.NOT_CHALLENGING_STATUS);
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        int reward = mm.getMission().getReward();
        mm.complete();
        member.addPoint(reward);

        return MissionConverter.toCompleteMissionRes(mm, reward, member.getPoint());
    }

    // 동네별 미션 진행률
    public MissionResDTO.HomeMissions getHomeMissions(Long memberId, String addressParam) {
        if (!memberRepository.existsById(memberId)) {
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }

        Address address = parseAddress(addressParam);

        long totalCount = memberMissionRepository.countByMemberAndAddress(memberId, address);
        long completedCount = memberMissionRepository
                .countByMemberAndStatusAndAddress(memberId, MissionStatus.COMPLETED, address);

        List<MemberMission> challenging = memberMissionRepository
                .findByMemberAndStatusAndAddress(memberId, MissionStatus.CHALLENGING, address);

        return MissionConverter.toHomeMissions(address, completedCount, totalCount, challenging);
    }

    private MissionStatus parseStatus(String statusParam) {
        try {
            return MissionStatus.valueOf(statusParam.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MissionException(MissionErrorCode.INVALID_MISSION_STATUS);
        }
    }

    private Address parseAddress(String addressParam) {
        if (addressParam == null || addressParam.isBlank()) {
            return Address.OTHER;
        }
        try {
            return Address.valueOf(addressParam.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Address.OTHER;
        }
    }
}
