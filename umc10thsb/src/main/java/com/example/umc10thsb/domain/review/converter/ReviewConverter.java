package com.example.umc10thsb.domain.review.converter;

import com.example.umc10thsb.domain.member.entity.Member;
import com.example.umc10thsb.domain.review.dto.ReviewReqDTO;
import com.example.umc10thsb.domain.review.dto.ReviewResDTO;
import com.example.umc10thsb.domain.review.entity.Review;
import com.example.umc10thsb.domain.review.enums.ReviewSortBy;
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

    // 내가 작성한 리뷰 - 커서 페이지네이션

    // 내가 작성한 리뷰 element
    public static ReviewResDTO.MyReviewItem toMyReviewItem(Review review) {
        Store store = review.getStore();
        return ReviewResDTO.MyReviewItem.builder()
                .reviewId(review.getId())
                .storeId(store != null ? store.getId() : null)
                .storeName(store != null ? store.getName() : null)
                .star(review.getStar())
                .title(review.getTitle())
                .content(review.getContent())
                .createdAt(review.getCreatedAt() != null ? review.getCreatedAt().toString() : null)
                .build();
    }

    // Review List
    public static ReviewResDTO.MyReviewCursorList toMyReviewCursorList(List<Review> fetched,
                                                                       ReviewSortBy sortBy,
                                                                       int size) {
        boolean hasNext = fetched.size() > size;
        List<Review> page = hasNext ? fetched.subList(0, size) : fetched;

        List<ReviewResDTO.MyReviewItem> items = page.stream()
                .map(ReviewConverter::toMyReviewItem)
                .toList();

        Long nextCursorId = null;
        Integer nextCursorStar = null;
        if (hasNext && !page.isEmpty()) {
            Review last = page.get(page.size() - 1);
            nextCursorId = last.getId();
            if (sortBy == ReviewSortBy.STAR) {
                nextCursorStar = last.getStar();
            }
        }

        return ReviewResDTO.MyReviewCursorList.builder()
                .reviews(items)
                .sortBy(sortBy.name())
                .size(size)
                .nextCursorId(nextCursorId)
                .nextCursorStar(nextCursorStar)
                .hasNext(hasNext)
                .build();
    }
}
