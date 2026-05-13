package com.example._th.domain.member.repository;

import com.example._th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 마이페이지용: 회원 정보와 리뷰 개수 등을 조회 (필요 시)
    @Query("SELECT m FROM Member m WHERE m.id = :memberId")
    Optional<Member> findMemberById(@Param("memberId") Long memberId);
}