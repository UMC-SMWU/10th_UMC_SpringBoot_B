package com.example._th.domain.mission.entity;
import com.example._th.global.common.BaseEntity; // BaseEntity가 위치한 경로
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "store")
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 20)
    private String managerNumber;

    @Column(length = 255)
    private String detailAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
}