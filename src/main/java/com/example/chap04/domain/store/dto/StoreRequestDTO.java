package com.example.chap04.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StoreRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateStoreRequest {
        private String name;
        private Long managerNumber;
        private String detailAddress;
        private Long locationId;
    }
}
