package com.example.umc10thsb.domain.member.repository;

import com.example.umc10thsb.domain.member.entity.Member;
import com.example.umc10thsb.domain.member.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);

    Optional<Member> findBySocialTypeAndSocialUid(SocialType socialType, String socialUid);
}
