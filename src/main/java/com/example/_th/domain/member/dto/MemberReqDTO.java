package com.example._th.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

public class MemberReqDTO {

    public record JoinDTO(
            String name,
            String gender,
            String birth,
            String address,
            String email
    ) {}
}



import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class MemberRequestDto {

    // 💡 [2단계] 회원가입 전체 데이터를 담는 큰 가방
    @Getter
    @Setter
    public static class JoinDto {
        private TermsAgreeDto agree;       // 약관 동의 정보 뭉치 (아래 클래스)
        private String name;               // 이름 ("test")
        private String gender;             // 성별 ("MALE")
        private String birth;              // 생년월일 ("2026-03-23")
        private String address;            // 주소
        private String detailAddress;      // 상세주소
        private List<String> foodList;     // 선호 음식 리스트 (["NONE"])
        private String email;              // 이메일 아이디 ("test@t.t")
        private String password;           // 비밀번호 ("test")
    }

    // 💡 [2단계] 약관 동의 항목들만 따로 묶은 작은 가방
    @Getter
    @Setter
    public static class TermsAgreeDto {
        private boolean age;       // 만 14세 이상 동의
        private boolean service;   // 서비스 이용약관 동의
        private boolean privacy;   // 개인정보 처리방침 동의
        private boolean location;  // 위치정보 제공 동의
        private boolean marketing; // 마케팅 수신 동의
    }
}