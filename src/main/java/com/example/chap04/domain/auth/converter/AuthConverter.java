package com.example.chap04.domain.auth.converter;

import com.example.chap04.domain.auth.dto.AuthResponseDTO;

public class AuthConverter {

    public static AuthResponseDTO.LoginResponse toLoginResponse(String accessToken) {
        return AuthResponseDTO.LoginResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
