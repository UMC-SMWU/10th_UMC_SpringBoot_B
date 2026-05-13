package com.example._th.domain.member.dto;

public class MemberReqDTO {

    public record JoinDTO(
            String name,
            String gender,
            String birth,
            String address,
            String email
    ) {}
}
