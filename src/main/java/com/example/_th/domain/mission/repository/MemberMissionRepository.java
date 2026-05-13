package com.example._th.domain.mission.repository;


import com.example._th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 미션 1번을 위한 핵심 메서드
    // 특정 회원(memberId)의 미션 중 특정 상태(status)인 데이터를 페이징해서 가져옵니다.
    Page<MemberMission> findAllByMemberIdAndStatus(Long memberId, String status, Pageable pageable);
}