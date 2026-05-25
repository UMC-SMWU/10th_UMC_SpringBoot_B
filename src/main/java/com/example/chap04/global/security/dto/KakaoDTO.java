package com.example.chap04.global.security.dto;

import com.example.chap04.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KakaoDTO implements OAuthDTO {

    private String socialUid;
    private String email;
    private String name;

    @Override
    public Member.SocialType getSocialType() {
        return Member.SocialType.KAKAO;
    }
}
