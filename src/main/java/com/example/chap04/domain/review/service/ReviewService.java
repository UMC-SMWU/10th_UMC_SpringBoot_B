package com.example.chap04.domain.review.service;

import com.example.chap04.domain.review.Converter.ReviewConverter;
import com.example.chap04.domain.review.dto.ReviewRequestDTO;
import com.example.chap04.domain.review.dto.ReviewResponseDTO;
import com.example.chap04.domain.review.entity.Reply;
import com.example.chap04.domain.review.entity.Review;
import com.example.chap04.domain.review.entity.ReviewPhoto;
import com.example.chap04.domain.review.repository.ReviewRepository;
import com.example.chap04.global.common.paging.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public PageResponse<ReviewResponseDTO.ReviewInfo> getReviewListByStoreId(Long storeId, Pageable pageable) {
        /*
        Page<Review>
            ├─ content: Review 10개
            ├─ page: 0
            ├─ size: 10
            ├─ totalElements: 전체 리뷰 개수
            └─ totalPages: 전체 페이지 수

            content: reviewPage.getContent()
            나머지 pagable 정보들: PageResponse.of(reviews, reviewPage)안에서 PageInfo.from(page)로 정리돼서 반환
        */
        Page<Review> reviewPage = reviewRepository.findPageByStoreId(storeId, pageable);

        List<ReviewResponseDTO.ReviewInfo> reviews = reviewPage.getContent().stream()
                .map(this::toReviewInfo)
                .toList();

        return PageResponse.of(reviews, reviewPage);
    }

    private ReviewResponseDTO.ReviewInfo toReviewInfo(Review review) {
        return ReviewResponseDTO.ReviewInfo.builder()
                .reviewId(review.getId())
                .memberId(review.getMember().getId())
                .nickname(review.getMember().getName())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .photos(toReviewPhotoInfoList(review.getReviewPhotos()))
                .reply(toReplyInfo(review.getReply()))
                .build();
    }

    private List<ReviewResponseDTO.ReviewPhotoInfo> toReviewPhotoInfoList(List<ReviewPhoto> reviewPhotos) {
        return reviewPhotos.stream()
                .map(reviewPhoto -> ReviewResponseDTO.ReviewPhotoInfo.builder()
                        .reviewPhotoId(reviewPhoto.getId())
                        .photoUrl(reviewPhoto.getPhotoUrl())
                        .build())
                .toList();
    }

    private ReviewResponseDTO.ReviewReplyInfo toReplyInfo(Reply reply) {
        if (reply == null) {
            return null;
        }

        return ReviewResponseDTO.ReviewReplyInfo.builder()
                .replyId(reply.getId())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .build();
    }

    // ID순 조회
    public ReviewResponseDTO.MyReviewCursorResponse getMyReviewsById(
            ReviewRequestDTO.MyReviewCursorRequest request
    ) {
        int size = request.getSize() == null ? 10 : request.getSize();

        Pageable pageable = PageRequest.of(0, size);

        Slice<Review> reviewSlice;

        if (request.getCursorReviewId() == null) {
            reviewSlice = reviewRepository.findByMember_IdOrderByIdDesc(
                    request.getMemberId(),
                    pageable
            );
        } else {
            reviewSlice = reviewRepository.findByMember_IdAndIdLessThanOrderByIdDesc(
                    request.getMemberId(),
                    request.getCursorReviewId(),
                    pageable
            );
        }

        return ReviewConverter.toMyReviewCursorResponse(reviewSlice);
    }

    // 별점순 조회
    public ReviewResponseDTO.MyReviewCursorResponse getMyReviewsByStar(
            ReviewRequestDTO.MyReviewCursorRequest request
    ) {
        int size = request.getSize() == null ? 10 : request.getSize();

        Pageable pageable = PageRequest.of(0, size);

        Slice<Review> reviewSlice;

        if (request.getCursorStar() == null || request.getCursorReviewId() == null) {
            reviewSlice = reviewRepository.findByMember_IdOrderByStarDescIdDesc(
                    request.getMemberId(),
                    pageable
            );
        } else {
            reviewSlice = reviewRepository.findMyReviewsByStarCursor(
                    request.getMemberId(),
                    request.getCursorStar(),
                    request.getCursorReviewId(),
                    pageable
            );
        }

        return ReviewConverter.toMyReviewCursorResponse(reviewSlice);
    }
}