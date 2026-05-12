package com.example.umc10thsb.domain.mission.repository;

import com.example.umc10thsb.domain.mission.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
