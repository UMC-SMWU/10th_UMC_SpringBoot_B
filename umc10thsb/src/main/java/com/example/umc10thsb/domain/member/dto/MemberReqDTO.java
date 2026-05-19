package com.example.umc10thsb.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class MemberReqDTO {

    // 회원가입
    public record SignUp(
            @NotBlank(message = "name은 필수입니다.")
            String name,

            @NotBlank(message = "email은 필수입니다.")
            @Email(message = "올바른 이메일 형식이어야 합니다.")
            String email,

            @NotBlank(message = "password는 필수입니다.")
            @Size(min = 8, max = 30, message = "password는 8자 이상 30자 이하여야 합니다.")
            String password,

            @NotBlank(message = "phoneNumber는 필수입니다.")
            String phoneNumber,

            String gender,
            List<Long> preferredFoodIds,
            List<Long> agreedTermIds
    ) {}

    // 마이페이지 수정
    public record UpdateInfo(
            String name,
            String nickname,
            String profileUrl,
            String phoneNumber
    ) {}
}
