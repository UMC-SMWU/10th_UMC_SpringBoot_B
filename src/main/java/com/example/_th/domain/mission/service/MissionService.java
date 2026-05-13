package com.example._th.domain.mission.service;

import com.example._th.domain.mission.converter.MissionConverter;
import com.example._th.domain.mission.dto.MissionReqDTO;
import com.example._th.domain.mission.dto.MissionResDTO;
import com.example._th.domain.mission.entity.mapping.MemberMission;
import com.example._th.domain.mission.repository.MemberMissionRepository;
import com.example._th.web.controller.MissionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MemberMissionRepository memberMissionRepository;
    private final MemberMissionRepository MemberMissionRepository;

    public Page<MemberMission> getMyMissionList(Long memberId, MissionStatus status, Integer page) {
        // 페이지는 보통 0번부터 시작해요! (size는 한 페이지에 보여줄 개수)
        Pageable pageable = PageRequest.of(page, 10);

        return memberMissionRepository.findAllByMemberIdAndStatus(memberId, status, pageable);
    }

    public MissionResDTO.MissionPreViewListDTO getMissionList(MissionReqDTO.GetListDTO request, Integer page) {

        // 1. 페이징 설정 (0번 페이지부터 시작하므로 page - 1 처리)
        // 한 페이지에 10개씩 보여준다고 가정
        PageRequest pageRequest = PageRequest.of(page, 10);

        // 2. 레포지토리 호출
        // 특정 멤버의 미션 중 상태(status)가 일치하는 것들을 가져옴
        Page<MemberMission> missionPage = memberMissionRepository.findAllByMemberIdAndStatus(
                request.memberId(),
                request.status(),
                pageRequest
        );

        // 3. 컨버터를 통해 DTO로 변환하여 리턴
        return MissionConverter.toMissionPreViewListDTO(missionPage);
    }
}