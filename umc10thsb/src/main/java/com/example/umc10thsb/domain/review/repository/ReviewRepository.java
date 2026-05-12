package com.example.umc10thsb.domain.review.repository;

import com.example.umc10thsb.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
}
