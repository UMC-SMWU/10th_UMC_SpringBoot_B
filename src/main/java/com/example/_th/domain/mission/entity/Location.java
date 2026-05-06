package com.example._th.domain.mission.entity;
import com.example._th.global.common.BaseEntity; // BaseEntity가 위치한 경로
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "location")
public class Location extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;
}