package com.example.chap04.domain.member.repository;

import com.example.chap04.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String username);

    boolean existsByEmail(String email);

    Optional<Member> findBySocialTypeAndSocialUid(Member.SocialType socialType, String socialUid);
}