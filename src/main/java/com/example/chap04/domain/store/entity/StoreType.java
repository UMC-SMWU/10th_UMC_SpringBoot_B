package com.example.chap04.domain.store.entity;

import com.example.chap04.domain.location.entity.Location;
import com.example.chap04.domain.mission.entity.Mission;
import com.example.chap04.domain.review.entity.Review;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class StoreType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "manager_number", nullable = false)
    private Long managerNumber;

    @Column(name = "detail_address", nullable = false)
    private String detailAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @OneToMany(
            mappedBy = "store",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    private List<Mission> missions = new ArrayList<>();

    @OneToMany(
            mappedBy = "store",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    private List<Review> reviews = new ArrayList<>();
}
