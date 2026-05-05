package com.example.chap04.domain.member.entity;

import com.example.chap04.domain.food.entity.MemberFood;
import com.example.chap04.domain.mission.entity.MemberMission;
import com.example.chap04.domain.review.entity.Review;
import com.example.chap04.domain.term.entity.MemberTerm;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 5)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(20)")
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private Address address;

    @Column(name = "detail_address", nullable = false)
    private String detailAddress;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public enum Gender {
        MALE,
        FEMALE,
        NONE
    }

    public enum Address {
        GANGNAM_GU,
        JONGNO_GU,
        SONGPA_GU,
        MAPO_GU,
        YONGSAN_GU
    }

    //// 관계 ////
    @OneToMany(
            mappedBy = "member",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    List<MemberMission> memberMissions = new ArrayList<>();

    @OneToMany(
            mappedBy = "member",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    List<MemberFood> memberFoods = new ArrayList<>();

    @OneToMany(
            mappedBy = "member",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    List<MemberTerm> memberTerms = new ArrayList<>();

    @OneToMany(
            mappedBy = "member",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    List<Review> reviews = new ArrayList<>();
}
