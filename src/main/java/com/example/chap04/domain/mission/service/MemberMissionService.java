package com.example.chap04.domain.mission.service;

import com.example.chap04.domain.mission.converter.MemberMissionConverter;
import com.example.chap04.domain.mission.dto.MemberMissionRequestDTO;
import com.example.chap04.domain.mission.dto.MemberMissionResponseDTO;
import com.example.chap04.domain.mission.entity.MemberMission;
import com.example.chap04.domain.mission.repository.MemberMissionRepository;
import com.example.chap04.global.common.paging.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionService {

    private final MemberMissionRepository  memberMissionRepository;

    public PageResponse<MemberMissionResponseDTO.MyMissionResponseDto> getMyMissions(
            MemberMissionRequestDTO.MyMissionRequestDto request,
            Integer page,
            Integer size
    ) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.DESC, "id")
        );

        Page<MemberMission> memberMissionPage =
                memberMissionRepository.findByMember_IdAndIsCompleteFalse(
                        request.getMemberId(),
                        pageable
                );

        return MemberMissionConverter.toMyMissionPageResponse(memberMissionPage);
    }
}
