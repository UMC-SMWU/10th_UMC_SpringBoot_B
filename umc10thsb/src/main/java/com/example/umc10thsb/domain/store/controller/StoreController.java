package com.example.umc10thsb.domain.store.controller;

import com.example.umc10thsb.domain.mission.dto.MissionReqDTO;
import com.example.umc10thsb.domain.mission.dto.MissionResDTO;
import com.example.umc10thsb.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10thsb.domain.store.dto.StoreReqDTO;
import com.example.umc10thsb.domain.store.dto.StoreResDTO;
import com.example.umc10thsb.domain.store.exception.code.StoreSuccessCode;
import com.example.umc10thsb.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Store", description = "가게 관련 API")
@RestController
@RequestMapping("/api/stores")
public class StoreController {

    // 가게 추가
    @Operation(summary = "가게 추가", description = "신규 가게를 등록한다.")
    @PostMapping
    public ApiResponse<StoreResDTO.CreateStore> createStore(
            @Valid @RequestBody StoreReqDTO.CreateStore request
    ) {
        StoreResDTO.CreateStore response = StoreResDTO.CreateStore.builder()
                .storeId(1L)
                .name(request.name())
                .address(request.address())
                .build();

        return ApiResponse.onSuccess(StoreSuccessCode.STORE_CREATED, response);
    }

    // 가게에 미션 추가
    @Operation(summary = "가게에 미션 추가", description = "특정 가게에 새로운 미션을 등록한다.")
    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResDTO.CreateMission> addMissionToStore(
            @PathVariable Long storeId,
            @Valid @RequestBody MissionReqDTO.CreateMission request
    ) {
        MissionResDTO.CreateMission response = MissionResDTO.CreateMission.builder()
                .missionId(1L)
                .storeId(storeId)
                .title(request.title())
                .build();

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_CREATED, response);
    }
}
