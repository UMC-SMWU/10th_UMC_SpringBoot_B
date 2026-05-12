package com.example.umc10thsb.domain.mission.repository;

import com.example.umc10thsb.domain.mission.entity.mapping.MemberMission;
import com.example.umc10thsb.domain.mission.enums.Address;
import com.example.umc10thsb.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 내 미션 목록 - 진행중/완료 상태별 페이지 조회
    @Query(
            value = "SELECT mm FROM MemberMission mm " +
                    "JOIN FETCH mm.mission m " +
                    "JOIN FETCH m.store s " +
                    "WHERE mm.member.id = :memberId AND mm.status = :status " +
                    "ORDER BY mm.createdAt DESC",
            countQuery = "SELECT count(mm) FROM MemberMission mm " +
                    "WHERE mm.member.id = :memberId AND mm.status = :status"
    )
    Page<MemberMission> findPageByMemberAndStatus(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );

    // 상태 필터가 없는 경우 - 전체 페이지 조회
    @Query(
            value = "SELECT mm FROM MemberMission mm " +
                    "JOIN FETCH mm.mission m " +
                    "JOIN FETCH m.store s " +
                    "WHERE mm.member.id = :memberId " +
                    "ORDER BY mm.createdAt DESC",
            countQuery = "SELECT count(mm) FROM MemberMission mm " +
                    "WHERE mm.member.id = :memberId"
    )
    Page<MemberMission> findPageByMember(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // 미션 상세/완료 처리 - (회원, 미션) 조합 단건 조회
    @Query("SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE mm.member.id = :memberId AND mm.mission.id = :missionId")
    Optional<MemberMission> findByMemberAndMission(
            @Param("memberId") Long memberId,
            @Param("missionId") Long missionId
    );

    // 홈 화면 - 내가 도전한 미션 중 특정 동네에 속한 미션의 상태별 카운트
    @Query("SELECT count(mm) FROM MemberMission mm " +
            "WHERE mm.member.id = :memberId " +
            "AND mm.status = :status " +
            "AND mm.mission.store.location.address = :address")
    long countByMemberAndStatusAndAddress(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            @Param("address") Address address
    );

    // 홈 화면 - 내가 도전한 미션 중 특정 동네에 속한 모든 미션 카운트
    @Query("SELECT count(mm) FROM MemberMission mm " +
            "WHERE mm.member.id = :memberId " +
            "AND mm.mission.store.location.address = :address")
    long countByMemberAndAddress(
            @Param("memberId") Long memberId,
            @Param("address") Address address
    );

    // 홈 화면 - 내가 진행중인 미션 중 특정 동네에 속한 미션 목록
    @Query("SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.mission m " +
            "JOIN FETCH m.store s " +
            "JOIN FETCH s.location l " +
            "WHERE mm.member.id = :memberId " +
            "AND mm.status = :status " +
            "AND l.address = :address " +
            "ORDER BY mm.createdAt DESC")
    List<MemberMission> findByMemberAndStatusAndAddress(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            @Param("address") Address address
    );
}
