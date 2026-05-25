package com.example.chap04.global.security.dto;

import com.example.chap04.domain.member.entity.Member;

public interface OAuthDTO {

    String getSocialUid();

    String getEmail();

    String getName();

    Member.SocialType getSocialType();
}
