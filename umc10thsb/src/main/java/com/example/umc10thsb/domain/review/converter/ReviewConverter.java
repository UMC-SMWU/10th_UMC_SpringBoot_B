package com.example.umc10thsb.domain.review.converter;

import com.example.umc10thsb.domain.member.entity.Member;
import com.example.umc10thsb.domain.review.dto.ReviewReqDTO;
import com.example.umc10thsb.domain.review.dto.ReviewResDTO;
import com.example.umc10thsb.domain.review.entity.Review;
import com.example.umc10thsb.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.util.List;

public class ReviewConverter {

    private ReviewConverter() {}

    // 리뷰 작성 요청 DTO + 작성자/가게 → Review 엔티티
    public static Review toReview(ReviewReqDTO.CreateReview req, Member member, Store store) {
        return Review.builder()
                .member(member)
                .store(store)
                .title(req.title())
                .star(req.star())
                .content(req.content())
                .build();
    }

    public static ReviewResDTO.CreateReview toCreateReviewRes(Review review) {
        return ReviewResDTO.CreateReview.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .title(review.getTitle())
                .star(review.getStar())
                .content(review.getContent())
                .build();
    }

    // Review → 리뷰 목록 element
    public static ReviewResDTO.ReviewItem toReviewItem(Review review) {
        Member writer = review.getMember();
        String writerName = writer != null
                ? (writer.getNickname() != null ? writer.getNickname() : writer.getName())
                : null;

        return ReviewResDTO.ReviewItem.builder()
                .reviewId(review.getId())
                .writerName(writerName)
                .star(review.getStar())
                .title(review.getTitle())
                .content(review.getContent())
                .createdAt(review.getCreatedAt() != null ? review.getCreatedAt().toString() : null)
                .build();
    }

    // Page<Review> → 페이지네이션된 응답
    public static ReviewResDTO.ReviewList toReviewList(Page<Review> page) {
        List<ReviewResDTO.ReviewItem> items = page.getContent().stream()
                .map(ReviewConverter::toReviewItem)
                .toList();

        return ReviewResDTO.ReviewList.builder()
                .reviews(items)
                .page(page.getNumber() + 1)
                .size(page.getSize())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .hasNext(page.hasNext())
                .build();
    }
}
