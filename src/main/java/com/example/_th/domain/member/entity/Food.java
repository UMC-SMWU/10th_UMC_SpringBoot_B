package com.example._th.domain.member.entity;

import com.example._th.domain.member.enums.FoodName;
import jakarta.persistence.*;
import lombok.*;


@Entity // 1
@Getter // 2
@Builder // 3
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 4
@AllArgsConstructor // 5
@Table(name = "food") // 6
public class Food {

    @Id // 7
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 8
    private Long id;

    @Column(name = "name", nullable = false) // 9
    @Enumerated(EnumType.STRING) // 10
    private FoodName name;
}
