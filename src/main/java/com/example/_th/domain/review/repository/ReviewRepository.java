package com.example._th.domain.review.repository;


import com.example._th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // save() 메서드는 이미 이 안에 내장되어 있어서 안 써도 됩니다.
}

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 처음 조회할 때 (lastReviewId가 null일 때)
    Slice<Review> findAllByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);

    // 두 번째부터 (lastReviewId보다 작은 것들 중 최신순)
    Slice<Review> findAllByMemberIdAndIdLessThanOrderByIdDesc(Long memberId, Long lastId, Pageable pageable);
}