package com.example.chap04.domain.auth.controller;

import com.example.chap04.domain.auth.converter.AuthConverter;
import com.example.chap04.domain.auth.dto.AuthRequestDTO;
import com.example.chap04.domain.auth.dto.AuthResponseDTO;
import com.example.chap04.domain.auth.service.AuthService;
import com.example.chap04.global.api.ApiResponse;
import com.example.chap04.global.api.GeneralSuccessCode;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<Void>> signin(
            @RequestBody AuthRequestDTO.signin request
    ) {
        authService.signin(request);
        return ApiResponse.onSuccessResponse(GeneralSuccessCode.POST_SUCCESS, null);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDTO.LoginResponse>> login(
            @RequestBody AuthRequestDTO.login request
    ) {
        String accessToken = authService.login(request);
        return ApiResponse.onSuccessResponse(GeneralSuccessCode.POST_SUCCESS, AuthConverter.toLoginResponse(accessToken));
    }
}

