package com.example.chap04.global.security.service;

import com.example.chap04.domain.member.converter.MemberConverter;
import com.example.chap04.domain.member.entity.Member;
import com.example.chap04.domain.member.repository.MemberRepository;
import com.example.chap04.global.api.GeneralErrorCode;
import com.example.chap04.global.api.ProjectException;
import com.example.chap04.global.security.dto.KakaoDTO;
import com.example.chap04.global.security.dto.OAuthDTO;
import com.example.chap04.global.security.entity.AuthMember;
import com.example.chap04.global.security.entity.OAuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public UserDetails loadUserByUidAndSocialType(
            Member.SocialType socialType,
            String username
    ) throws UsernameNotFoundException {
        Member member = memberRepository.findBySocialTypeAndSocialUid(socialType, username)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.MEMBER_NOT_FOUND));

        return new AuthMember(member);
    }

    @Override
    public OAuth2User loadUser(
            OAuth2UserRequest userRequest
    ) throws OAuth2AuthenticationException {

        OAuth2User oAuthUser = super.loadUser(userRequest);

        Member.SocialType providerId;

        try {
            providerId = Member.SocialType.valueOf(
                    userRequest.getClientRegistration()
                            .getRegistrationId()
                            .toUpperCase()
            );
        } catch (IllegalArgumentException e) {
            throw new ProjectException(GeneralErrorCode.BAD_REQUEST);
        }

        OAuthDTO dto;

        switch (providerId) {
            case KAKAO -> {
                Map<String, Object> kakaoAccount = oAuthUser.getAttribute("kakao_account");

                if (kakaoAccount == null) {
                    throw new ProjectException(GeneralErrorCode.BAD_REQUEST);
                }

                Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

                Object rawSocialUid = oAuthUser.getAttribute("id");
                String socialUid = rawSocialUid.toString();
                String email = String.valueOf(kakaoAccount.get("email"));

                String name = profile != null && profile.get("nickname") != null
                        ? String.valueOf(profile.get("nickname"))
                        : email;

                dto = new KakaoDTO(socialUid, email, name);
            }

            default -> throw new ProjectException(GeneralErrorCode.BAD_REQUEST);
        }

        Member member = memberRepository.findBySocialTypeAndSocialUid(
                        providerId,
                        dto.getSocialUid()
                )
                .orElseGet(() -> {
                    Member newMember = MemberConverter.toMember(dto);
                    return memberRepository.save(newMember);
                });

        return new OAuthMember(member, oAuthUser.getAttributes());
    }
}