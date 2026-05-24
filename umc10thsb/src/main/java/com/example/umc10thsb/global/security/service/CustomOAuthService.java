package com.example.umc10thsb.global.security.service;

import com.example.umc10thsb.domain.member.converter.MemberConverter;
import com.example.umc10thsb.domain.member.entity.Member;
import com.example.umc10thsb.domain.member.enums.SocialType;
import com.example.umc10thsb.domain.member.exception.MemberException;
import com.example.umc10thsb.domain.member.exception.code.MemberErrorCode;
import com.example.umc10thsb.domain.member.repository.MemberRepository;
import com.example.umc10thsb.global.security.dto.KakaoDTO;
import com.example.umc10thsb.global.security.dto.OAuthDTO;
import com.example.umc10thsb.global.security.entity.OAuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuthService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    @SuppressWarnings("unchecked")
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuthMember = super.loadUser(userRequest);

        SocialType providerId;
        String socialUid;
        Map<String, Object> attributes = oAuthMember.getAttribute("kakao_account");
        Map<String, Object> profile = attributes != null
                ? (Map<String, Object>) attributes.get("profile")
                : Map.of();
        try {
            providerId = SocialType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
            Object idAttr = oAuthMember.getAttribute("id");
            socialUid = String.valueOf(idAttr);
        } catch (IllegalArgumentException e) {
            throw new MemberException(MemberErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
        }

        OAuthDTO dto;
        switch (providerId) {
            case KAKAO -> {
                String email = attributes != null && attributes.get("email") != null
                        ? attributes.get("email").toString()
                        : null;
                String name = profile.get("nickname") != null
                        ? profile.get("nickname").toString()
                        : null;
                dto = new KakaoDTO(socialUid, email, name);
            }
            default -> throw new MemberException(MemberErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
        }

        Member member = memberRepository.findBySocialTypeAndSocialUid(providerId, socialUid)
                .orElseGet(() -> {
                    Member newMember = MemberConverter.toMember(dto);
                    memberRepository.save(newMember);
                    return newMember;
                });
        return new OAuthMember(member, oAuthMember.getAttributes());
    }
}
