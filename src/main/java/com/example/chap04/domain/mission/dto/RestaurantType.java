package com.example.chap04.domain.mission.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RestaurantType {
    CHINEZE("중식당");

    private final String name;
}
