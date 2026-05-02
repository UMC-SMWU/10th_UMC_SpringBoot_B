package com.example.umc10thsb.domain.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StoreReqDTO {

    // 가게 추가
    public record CreateStore(
            @NotBlank String name,
            @NotBlank String address,
            @NotNull Long locationId
    ) {}
}
