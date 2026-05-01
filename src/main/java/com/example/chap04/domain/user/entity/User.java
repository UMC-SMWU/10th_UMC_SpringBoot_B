package com.example.chap04.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class User {

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

    @Column(name = "social_uid", nullable = false)
    private String socialUid;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = false, columnDefinition = "varchar(20)")
    private SocialType socialType;

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

    public enum SocialType {
        KAKAO,
        NAVER,
        APPLE,
        GOOGLE
    }

    public enum Address {
        GANGNAM_GU,
        JONGNO_GU,
        SONGPA_GU,
        MAPO_GU,
        YONGSAN_GU
    }
}
