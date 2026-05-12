package com.example.umc10thsb.domain.mission.repository;

import com.example.umc10thsb.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 미션 단건 조회
    @Query("SELECT m FROM Mission m " +
            "JOIN FETCH m.store s " +
            "JOIN FETCH s.location " +
            "WHERE m.id = :missionId")
    Optional<Mission> findByIdWithStore(@Param("missionId") Long missionId);
}
