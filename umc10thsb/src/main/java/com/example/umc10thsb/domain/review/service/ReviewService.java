package com.example.umc10thsb.domain.review.service;

import com.example.umc10thsb.domain.member.entity.Member;
import com.example.umc10thsb.domain.member.exception.MemberException;
import com.example.umc10thsb.domain.member.exception.code.MemberErrorCode;
import com.example.umc10thsb.domain.member.repository.MemberRepository;
import com.example.umc10thsb.domain.review.converter.ReviewConverter;
import com.example.umc10thsb.domain.review.dto.ReviewReqDTO;
import com.example.umc10thsb.domain.review.dto.ReviewResDTO;
import com.example.umc10thsb.domain.review.entity.Review;
import com.example.umc10thsb.domain.review.enums.ReviewSortBy;
import com.example.umc10thsb.domain.review.exception.ReviewException;
import com.example.umc10thsb.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10thsb.domain.review.repository.ReviewRepository;
import com.example.umc10thsb.domain.store.entity.Store;
import com.example.umc10thsb.domain.store.exception.StoreException;
import com.example.umc10thsb.domain.store.exception.code.StoreErrorCode;
import com.example.umc10thsb.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    // 점포관리/리뷰 화면 - 가게에 리뷰 작성
    @Transactional
    public ReviewResDTO.CreateReview createReview(Long memberId, Long storeId, ReviewReqDTO.CreateReview req) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Review review = ReviewConverter.toReview(req, member, store);
        Review saved = reviewRepository.save(review);

        return ReviewConverter.toCreateReviewRes(saved);
    }

    // 점포관리/리뷰 화면 - 가게의 리뷰 목록 페이지네이션 조회
    public ReviewResDTO.ReviewList getStoreReviews(Long storeId, int page, int size) {
        if (!storeRepository.existsById(storeId)) {
            throw new StoreException(StoreErrorCode.STORE_NOT_FOUND);
        }

        Pageable pageable = PageRequest.of(Math.max(page - 1, 0), size);
        Page<Review> result = reviewRepository.findPageByStoreId(storeId, pageable);

        return ReviewConverter.toReviewList(result);
    }

     // 내가 작성한 리뷰 목록 - 커서 기반 페이지네이션
    public ReviewResDTO.MyReviewCursorList getMyReviews(
            Long memberId,
            ReviewSortBy sortBy,
            Long cursorId,
            Integer cursorStar,
            int size
    ) {
        // 회원 존재 검증
        if (!memberRepository.existsById(memberId)) {
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }

        int pageSize = Math.max(size, 1);

        Pageable pageable = PageRequest.of(0, pageSize + 1);

        List<Review> fetched;
        if (sortBy == ReviewSortBy.STAR) {
            boolean hasStarCursor = cursorStar != null;
            boolean hasIdCursor = cursorId != null;
            if (hasStarCursor ^ hasIdCursor) {
                throw new ReviewException(ReviewErrorCode.INVALID_CURSOR);
            }

            if (!hasStarCursor) {
                fetched = reviewRepository.findMyReviewsByStarFirstPage(memberId, pageable);
            } else {
                fetched = reviewRepository.findMyReviewsByStarAfterCursor(
                        memberId, cursorStar, cursorId, pageable);
            }
        } else {
            if (cursorId == null) {
                fetched = reviewRepository.findMyReviewsByIdFirstPage(memberId, pageable);
            } else {
                fetched = reviewRepository.findMyReviewsByIdAfterCursor(memberId, cursorId, pageable);
            }
        }

        return ReviewConverter.toMyReviewCursorList(fetched, sortBy, pageSize);
    }
}
