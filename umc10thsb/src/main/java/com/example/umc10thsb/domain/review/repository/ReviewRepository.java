package com.example.umc10thsb.domain.review.repository;

import com.example.umc10thsb.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 점포관리/리뷰 화면 - 특정 가게의 리뷰 목록 페이지 조회
    @Query(
            value = "SELECT r FROM Review r " +
                    "JOIN FETCH r.member " +
                    "WHERE r.store.id = :storeId " +
                    "ORDER BY r.createdAt DESC",
            countQuery = "SELECT count(r) FROM Review r WHERE r.store.id = :storeId"
    )
    Page<Review> findPageByStoreId(
            @Param("storeId") Long storeId,
            Pageable pageable
    );

    // 내가 작성한 리뷰
    // [ID 정렬] 첫 페이지
    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.store s " +
            "WHERE r.member.id = :memberId " +
            "ORDER BY r.id DESC")
    List<Review> findMyReviewsByIdFirstPage(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // [ID 정렬] 커서 이후
    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.store s " +
            "WHERE r.member.id = :memberId " +
            "AND r.id < :cursorId " +
            "ORDER BY r.id DESC")
    List<Review> findMyReviewsByIdAfterCursor(
            @Param("memberId") Long memberId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    // [STAR 정렬] 첫 페이지
    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.store s " +
            "WHERE r.member.id = :memberId " +
            "ORDER BY r.star DESC, r.id DESC")
    List<Review> findMyReviewsByStarFirstPage(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // [STAR 정렬] 커서 이후
    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.store s " +
            "WHERE r.member.id = :memberId " +
            "AND ( r.star < :cursorStar " +
            "      OR ( r.star = :cursorStar AND r.id < :cursorId ) ) " +
            "ORDER BY r.star DESC, r.id DESC")
    List<Review> findMyReviewsByStarAfterCursor(
            @Param("memberId") Long memberId,
            @Param("cursorStar") Integer cursorStar,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}
