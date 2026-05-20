package com.example._th.domain.member.entity;
import com.example._th.global.common.BaseEntity;
import com.example._th.domain.member.enums.Gender;
import com.example._th.domain.member.enums.SocialType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    private LocalDate birth;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false, length = 40)
    private String detailAddress;

    @Column(length = 50)
    private String email;

    @Column(length = 20)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15)")
    private SocialType socialType;

    @Builder.Default
    private Integer point = 0;

    private String password;
}
