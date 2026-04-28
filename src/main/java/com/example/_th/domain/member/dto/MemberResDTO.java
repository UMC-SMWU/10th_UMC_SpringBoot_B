package com.example._th.domain.member.dto;
import lombok.Builder;
import java.time.LocalDateTime;
public class MemberResDTO {

    @Builder
    public record JoinResultDTO(
            Long memberId,
            LocalDateTime createdAt
    ) {}
}
