package com.example._th.domain.mission.repository;

import com.example._th.domain.mission.entity.mapping.MemberMission;
import com.example._th.web.controller.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("SELECT mm FROM MemberMission mm JOIN FETCH mm.mission " +
            "WHERE mm.member.id = :memberId AND mm.status = :status")
    Page<MemberMission> findAllByMemberIdAndStatus(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );
}