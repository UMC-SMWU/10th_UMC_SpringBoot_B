package com.example.chap04.domain.mission.dto;

import com.example.chap04.domain.store.entity.StoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MissionResponseDTO {

    // 성공한 my mission 개수
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AchievedCountResponse {
        @JsonProperty("achieved_count")
        private Long achievedCount;
    }

    // 성공한 my mission 세부 정보
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlaceInfoDto {
        private String name;
        private RestaurantType type;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionInfoDto {
        private String conditional;
        private Integer point;
        private LocalDate deadline;
        @JsonProperty("is_complete")
        private Boolean isComplete;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyMissionDto {
        @JsonProperty("place_info")
        private PlaceInfoDto place;
        @JsonProperty("mission_info")
        private MissionInfoDto mission;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyMissionListResponseDto {
        @JsonProperty("my_missions")
        private List<MyMissionDto> missions;
    }
}
