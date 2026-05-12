package com.example.umc10thsb.domain.store.service;

import com.example.umc10thsb.domain.mission.converter.MissionConverter;
import com.example.umc10thsb.domain.mission.dto.MissionReqDTO;
import com.example.umc10thsb.domain.mission.dto.MissionResDTO;
import com.example.umc10thsb.domain.mission.entity.Location;
import com.example.umc10thsb.domain.mission.entity.Mission;
import com.example.umc10thsb.domain.mission.repository.LocationRepository;
import com.example.umc10thsb.domain.mission.repository.MissionRepository;
import com.example.umc10thsb.domain.store.converter.StoreConverter;
import com.example.umc10thsb.domain.store.dto.StoreReqDTO;
import com.example.umc10thsb.domain.store.dto.StoreResDTO;
import com.example.umc10thsb.domain.store.entity.Store;
import com.example.umc10thsb.domain.store.exception.StoreException;
import com.example.umc10thsb.domain.store.exception.code.StoreErrorCode;
import com.example.umc10thsb.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final LocationRepository locationRepository;
    private final MissionRepository missionRepository;

    // 점포관리 화면 - 가게 추가
    @Transactional
    public StoreResDTO.CreateStore createStore(StoreReqDTO.CreateStore req) {
        Location location = locationRepository.findById(req.locationId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.LOCATION_NOT_FOUND));

        Store store = StoreConverter.toStore(req, location);
        Store saved = storeRepository.save(store);

        return StoreConverter.toCreateStoreRes(saved);
    }

    // 점포관리 화면 - 가게에 미션 추가
    @Transactional
    public MissionResDTO.CreateMission addMissionToStore(Long storeId, MissionReqDTO.CreateMission req) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(req, store);
        Mission saved = missionRepository.save(mission);

        return MissionConverter.toCreateMissionRes(saved);
    }
}
