package com.example._th.domain.mission.entity;
import com.example._th.global.common.BaseEntity; // BaseEntity가 위치한 경로
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "mission")
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer point;

    private LocalDate deadline;

    @Column(columnDefinition = "TEXT")
    private String conditional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}