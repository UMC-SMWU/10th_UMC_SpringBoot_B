package com.example.chap04.domain.mission.entity;

import com.example.chap04.domain.store.entity.Store;
import com.example.chap04.global.common.TimeBaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mission")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Mission extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(nullable = false)
    private LocalDate deadline;

    @Lob
    @Column(name = "conditional", nullable = false)
    private String conditional;

    @Column(nullable = false)
    private Integer point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(
            mappedBy = "mission",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    private List<MemberMission> memberMissions = new ArrayList<>();

}
