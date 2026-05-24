package com.example.umc10thsb.global.security.handler;

import com.example.umc10thsb.domain.member.converter.MemberConverter;
import com.example.umc10thsb.domain.member.dto.MemberResDTO;
import com.example.umc10thsb.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10thsb.global.apiPayload.ApiResponse;
import com.example.umc10thsb.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10thsb.global.security.entity.AuthMember;
import com.example.umc10thsb.global.security.entity.OAuthMember;
import com.example.umc10thsb.global.security.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        BaseSuccessCode code = MemberSuccessCode.OK;

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getHttpStatus().value());

        OAuthMember member = (OAuthMember) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String accessToken = jwtUtil.createAccessToken(new AuthMember(member.getMember()));

        ApiResponse<MemberResDTO.Login> responseBody = ApiResponse.onSuccess(
                code,
                MemberConverter.toLogin(accessToken)
        );

        objectMapper.writeValue(response.getOutputStream(), responseBody);
    }
}
