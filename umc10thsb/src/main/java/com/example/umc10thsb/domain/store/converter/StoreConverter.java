package com.example.umc10thsb.domain.store.converter;

import com.example.umc10thsb.domain.mission.entity.Location;
import com.example.umc10thsb.domain.store.dto.StoreReqDTO;
import com.example.umc10thsb.domain.store.dto.StoreResDTO;
import com.example.umc10thsb.domain.store.entity.Store;

public class StoreConverter {

    private StoreConverter() {}

    // 가게 생성 요청 DTO + Location → Store 엔티티
    public static Store toStore(StoreReqDTO.CreateStore req, Location location) {
        return Store.builder()
                .location(location)
                .name(req.name())
                .address(req.address())
                .build();
    }

    public static StoreResDTO.CreateStore toCreateStoreRes(Store store) {
        return StoreResDTO.CreateStore.builder()
                .storeId(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .build();
    }
}
