package com.example.umc10thsb.domain.store.dto;

import lombok.Builder;

public class StoreResDTO {

    // 가게 생성 응답
    @Builder
    public record CreateStore(
            Long storeId,
            String name,
            String address
    ) {}
}
