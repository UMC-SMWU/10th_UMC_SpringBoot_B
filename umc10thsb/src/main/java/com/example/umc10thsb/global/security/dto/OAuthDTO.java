package com.example.umc10thsb.global.security.dto;

import com.example.umc10thsb.domain.member.enums.SocialType;

public interface OAuthDTO {
    SocialType getSocialType();
    String getSocialUid();
    String getSocialEmail();
    String getName();
}
