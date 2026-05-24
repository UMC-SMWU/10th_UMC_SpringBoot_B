package com.example.umc10thsb.domain.auth.controller;

import com.example.umc10thsb.domain.auth.dto.AuthReqDTO;
import com.example.umc10thsb.domain.auth.dto.AuthResDTO;
import com.example.umc10thsb.domain.auth.exception.code.AuthSuccessCode;
import com.example.umc10thsb.domain.auth.service.AuthService;
import com.example.umc10thsb.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "인증 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    // 로그인
    @Operation(summary = "로그인", description = "이메일/비밀번호로 로그인하고 Access Token 을 발급한다.")
    @PostMapping("/login")
    public ApiResponse<AuthResDTO.Login> login(
            @Valid @RequestBody AuthReqDTO.Login request
    ) {
        AuthResDTO.Login response = authService.login(request);
        return ApiResponse.onSuccess(AuthSuccessCode.AUTH_LOGIN_SUCCESS, response);
    }

    // 로그아웃
    @Operation(summary = "로그아웃", description = "SecurityContext 를 비운다. (클라이언트는 저장된 토큰을 삭제)")
    @PostMapping("/logout")
    public ApiResponse<Void> logout() {
        authService.logout();
        return ApiResponse.onSuccess(AuthSuccessCode.AUTH_LOGOUT_SUCCESS);
    }
}
