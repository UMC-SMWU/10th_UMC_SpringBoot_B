package com.example.chap04.global.security.filter;

import com.example.chap04.domain.member.entity.Member;
import com.example.chap04.global.api.ApiResponse;
import com.example.chap04.global.api.BaseCode;
import com.example.chap04.global.api.GeneralErrorCode;
import com.example.chap04.global.security.service.CustomUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            // Authorization 헤더에서 토큰 가져오기
            String token = request.getHeader("Authorization");

            // token이 없거나 Bearer로 시작하지 않으면 다음 필터로 넘기기
            if (token == null || !token.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            // "Bearer " 제거 후 순수 토큰 추출
            token = token.replace("Bearer ", "");

            // 토큰 유효성 검증
            if (jwtUtil.isValid(token)) {
                UserDetails user;
                Member.SocialType socialType = jwtUtil.getSocialType(token);

                if (socialType != null) {
                    // OAuth 토큰: subject = uid, claim에 social_type 존재
                    String uid = jwtUtil.getUid(token);
                    user = customUserDetailsService.loadUserByUidAndSocialType(socialType, uid);
                } else {
                    // 일반 로그인 토큰: subject = email
                    String email = jwtUtil.getEmail(token);
                    user = customUserDetailsService.loadUserByUsername(email);
                }

                Authentication auth = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception e) {
            // JWT 파싱/검증 중 발생한 예외만 여기서 처리
            ObjectMapper mapper = new ObjectMapper();
            BaseCode code = GeneralErrorCode.UNAUTHORIZED;

            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(code.getStatus().value());

            ApiResponse<Void> errorResponse = ApiResponse.onFailure(code, null);
            mapper.writeValue(response.getOutputStream(), errorResponse);
            return; // 예외 발생 시 필터 체인 중단
        }

        // try-catch 밖에서 실행 → 컨트롤러/서비스 예외는 GlobalExceptionAdvice가 처리
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();

        return path.startsWith("/oauth/authorize")
                || path.startsWith("/oauth/callback")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/swagger-resources")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/api/auth/signin")
                || path.startsWith("/api/auth/login");
    }
}
