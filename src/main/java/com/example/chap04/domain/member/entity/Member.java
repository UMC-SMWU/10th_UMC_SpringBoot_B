package com.example.chap04.domain.member.entity;

import com.example.chap04.domain.food.entity.MemberFood;
import com.example.chap04.domain.mission.entity.MemberMission;
import com.example.chap04.domain.review.entity.Review;
import com.example.chap04.domain.term.entity.MemberTerm;
import com.example.chap04.global.common.TimeBaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 500)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "login_type", nullable = false, columnDefinition = "varchar(20)")
    private LoginType loginType;

    @Column(nullable = false, length = 100)
    private String nickname;

    // Oauth 때매 잠시 꺼둠
    @Column(nullable = true, length = 100)
    private String password;

    // 일반 로그인 시 주석처리하기 (Oauth용)
    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = true, columnDefinition = "varchar(20)")
    private SocialType socialType;

    @Column(name = "social_uid", nullable = true, length = 100)
    private String socialUid;
    // Oauth

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

    @Column(nullable = false, length = 100)
    private String email;

    @Column(name = "phone_number", length = 30)
    private String phoneNumber;

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

    public enum LoginType {
        LOCAL,
        OAUTH
    }

    public enum SocialType {
        KAKAO,
        GOOGLE,
        NAVER
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
