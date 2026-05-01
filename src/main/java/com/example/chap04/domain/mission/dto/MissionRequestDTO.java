package com.example.chap04.domain.mission.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.List;

public class MissionRequestDTO {
    @Getter
    @Builder
    public static class achievedCountRequest{
        String achievedCount;
    }

    @Getter
    @Builder
    public static class placeInfoDto{
        String name;
        RestaurantType type;
    }

    @Getter
    @Builder
    public static class missionInfoDto{
        Text conditional;
        int point;
        LocalDate deadline;
        boolean is_complete;
    }

    @Getter
    @Builder
    public static class myMissionDto{
        @JsonProperty("place_info")
        placeInfoDto place;
        @JsonProperty("mission_info")
        missionInfoDto mission;
    }

    @Getter
    @Builder
    public static class myMissionListDto{
        @JsonProperty("my_missions")
        List<myMissionDto> missions;
    }
}
