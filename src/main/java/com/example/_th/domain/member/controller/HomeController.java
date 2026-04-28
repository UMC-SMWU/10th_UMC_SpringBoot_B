package com.example._th.domain.member.controller;
import com.example._th.domain.member.dto.HomeResDTO;
import com.example._th.domain.member.service.MemberService;
import com.example._th.domain.member.status.HomeSuccessCode;
import com.example._th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/me/home") // 홈 화면 경로
public class HomeController {

    private final MemberService memberService; // 서비스는 그대로 memberService를 써도 무방합니다!

    // 2. 홈 화면 조회
    @GetMapping("")
    public ApiResponse<HomeResDTO.HomeViewDTO> getHomeView() {
        // 아까 새로 만든 Home 전용 성공 코드 사용!
        return ApiResponse.onSuccess(HomeSuccessCode.HOME_OK, memberService.getHome());
    }
}