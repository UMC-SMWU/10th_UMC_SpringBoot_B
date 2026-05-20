package com.example._th.domain.member.controller;


import com.example._th.domain.member.dto.MemberRequestDto;
import com.example._th.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth") // 💡 주소창 앞에 자동으로 /auth가 붙어요!
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    // 💡 [4단계] Swagger에 나오던 [POST] /auth/sign-up API의 입구입니다!
    @PostMapping("/sign-up")
    public String signUp(@RequestBody MemberRequestDto.JoinDto request) {

        // 3단계 서비스로 손님이 준 가방을 전달하며 회원가입 로직 실행!
        memberService.joinMember(request);

        // 프론트엔드에게 성공 메시지를 텍스트로 보냅니다.
        // (💡 프로젝트에 ApiResponse 공통 규격 상자가 있다면 그걸로 감싸서 리턴해 주면 더 좋습니다!)
        return "회원가입 성공!";
    }
}