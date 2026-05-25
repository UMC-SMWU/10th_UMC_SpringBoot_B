package com.example.chap04.global.security.entity;

import com.example.chap04.domain.member.entity.Member;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class OAuthMember implements UserDetails, OAuth2User {

    private final Member member;
    private final Map<String, Object> attributes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return member.getSocialUid();
    }

    @Override
    public String getName() {
        return member.getSocialUid();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}