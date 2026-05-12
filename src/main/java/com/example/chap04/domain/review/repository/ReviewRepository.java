package com.example.chap04.domain.review.repository;

import com.example.chap04.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 어떤 row들을(value) 몇 개(countQuery) 가져올 것인가?
    @Query(
            value = """
                    select r
                    from Review r
                    join fetch r.member m
                    left join fetch r.reply rep
                    where r.store.id = :storeId
                    order by r.createdAt desc
                    """,
            countQuery = """
                    select count(r)
                    from Review r
                    where r.store.id = :storeId
                    """
    )
    Page<Review> findPageByStoreId(@Param("storeId") Long storeId, Pageable pageable);

    /**
     * ID순 첫 조회
     *
     * cursor가 없을 때 사용
     */
    Slice<Review> findByMember_IdOrderByIdDesc(
            Long memberId,
            Pageable pageable
    );

    /**
     * ID순 다음 조회
     *
     * cursorReviewId보다 작은 reviewId만 조회
     */
    Slice<Review> findByMember_IdAndIdLessThanOrderByIdDesc(
            Long memberId,
            Long cursorReviewId,
            Pageable pageable
    );

    /**
     * 별점순 첫 조회
     *
     * star DESC, id DESC
     */
    Slice<Review> findByMember_IdOrderByStarDescIdDesc(
            Long memberId,
            Pageable pageable
    );

    /**
     * 별점순 다음 조회
     *
     * star DESC, id DESC 기준
     *
     * 다음 페이지 조건:
     * 1. star가 cursorStar보다 작거나
     * 2. star가 같으면 id가 cursorReviewId보다 작아야 함
     */
    @Query("""
        select r
        from Review r
        where r.member.id = :memberId
          and (
                r.star < :cursorStar
                or (r.star = :cursorStar and r.id < :cursorReviewId)
          )
        order by r.star desc, r.id desc
    """)
    Slice<Review> findMyReviewsByStarCursor(
            @Param("memberId") Long memberId,
            @Param("cursorStar") Double cursorStar,
            @Param("cursorReviewId") Long cursorReviewId,
            Pageable pageable
    );
}
