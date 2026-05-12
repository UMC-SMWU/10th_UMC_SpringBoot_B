package com.example.chap04.domain.mission.dto;

import com.example.chap04.domain.mission.entity.StoreCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class MemberMissionResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyMissionResponseDto {
        private Integer point;
        private String storeName;
        private String conditional;
        private Boolean isComplete;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AvailableMissionResponseDto {
        private Long missionId;
        private String storeName;
        private StoreCategory category;
        private String conditional;
        private Integer point;
        private LocalDate deadline;
        private String dDay;
    }

}