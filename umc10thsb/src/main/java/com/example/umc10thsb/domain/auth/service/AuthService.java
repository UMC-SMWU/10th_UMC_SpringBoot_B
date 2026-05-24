package com.example.umc10thsb.domain.auth.service;

import com.example.umc10thsb.domain.auth.dto.AuthReqDTO;
import com.example.umc10thsb.domain.auth.dto.AuthResDTO;
import com.example.umc10thsb.domain.auth.exception.AuthException;
import com.example.umc10thsb.domain.auth.exception.code.AuthErrorCode;
import com.example.umc10thsb.global.security.entity.AuthMember;
import com.example.umc10thsb.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private static final String TOKEN_TYPE = "Bearer";

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    // 로그인 → JWT 발급
    public AuthResDTO.Login login(AuthReqDTO.Login req) {
        try {
            // 1) 이메일 / 비밀번호 검증
            Authentication authToken =
                    new UsernamePasswordAuthenticationToken(req.email(), req.password());
            Authentication authenticated = authenticationManager.authenticate(authToken);

            AuthMember principal = (AuthMember) authenticated.getPrincipal();

            // 2) JWT 발급
            String accessToken = jwtUtil.createAccessToken(principal);

            log.info("[Login] memberId={}, email={}",
                    principal.getMember().getId(), principal.getUsername());

            return AuthResDTO.Login.builder()
                    .memberId(principal.getMember().getId())
                    .email(principal.getUsername())
                    .name(principal.getMember().getName())
                    .accessToken(accessToken)
                    .tokenType(TOKEN_TYPE)
                    .expiresIn(jwtUtil.getAccessExpirationMillis())
                    .build();

        } catch (DisabledException e) {
            throw new AuthException(AuthErrorCode.ACCOUNT_DISABLED);
        } catch (AuthenticationException e) {
            throw new AuthException(AuthErrorCode.INVALID_CREDENTIALS);
        }
    }

    // 로그아웃
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}
