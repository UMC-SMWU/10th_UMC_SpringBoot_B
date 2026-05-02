package com.example.umc10thsb.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class MemberReqDTO {

    // 회원가입
    public record SignUp(
            @NotBlank String name,
            @NotBlank @Email String email,
            @NotBlank @Size(min = 8, max = 30) String password,
            @NotBlank String phoneNumber,
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
