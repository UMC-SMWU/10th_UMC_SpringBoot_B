package com.example.chap04.domain.mission.repository;

import com.example.chap04.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query(
            value = """
                    select m
                    from Mission m
                    join fetch m.store s
                    join fetch s.location l
                    where l.id = :locationId
                      and m.deadline >= :today
                    order by m.deadline asc
                    """,
            countQuery = """
                    select count(m)
                    from Mission m
                    join m.store s
                    join s.location l
                    where l.id = :locationId
                      and m.deadline >= :today
                    """
    )
    Page<Mission> findAvailableMissionsByLocation(
            @Param("locationId") Long locationId,
            @Param("today") LocalDate today,
            Pageable pageable
    );
}