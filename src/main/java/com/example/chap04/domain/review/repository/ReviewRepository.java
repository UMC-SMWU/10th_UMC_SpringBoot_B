package com.example.chap04.domain.review.repository;

import com.example.chap04.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
}
