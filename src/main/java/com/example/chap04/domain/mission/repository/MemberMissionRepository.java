package com.example.chap04.domain.mission.repository;

import com.example.chap04.domain.mission.entity.MemberMission;
import com.example.chap04.domain.mission.entity.MemberMissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query(
            value = """
                    select mm
                    from MemberMission mm
                    join fetch mm.mission m
                    join fetch m.store s
                    where mm.status = :status
                    """,
            countQuery = """
                    select count(mm)
                    from MemberMission mm
                    where mm.status = :status
                    """
    )
    Page<MemberMission> findMyMissions(
            MemberMissionStatus status,
            Pageable pageable
    );
}