package com.example._th.domain.review.service;

import com.example._th.domain.mission.entity.Store;
import com.example._th.domain.review.converter.ReviewConverter;
import com.example._th.domain.review.dto.ReviewReqDTO;
import com.example._th.domain.review.dto.ReviewResDTO;
import com.example._th.domain.review.entity.Review;
import com.example._th.domain.review.repository.ReviewRepository;
import com.example._th.domain.review.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    public Review createReview(Long storeId,
                               ReviewReqDTO.CreateReviewDTO request) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게가 없습니다."));

        // 1. DTO(받은 데이터)를 엔티티(DB 객체)로 바꿉니다.
        Review review = Review.builder()
                .content(request.getContent()) // 리뷰 내용
                .score(request.getScore())// 별점
                .store(store)
                .build();

        // 2. 리포지토리를 통해 저장합니다.
        return reviewRepository.save(review);
    }

    // 미션 2: 내가 작성한 리뷰 목록 조회
    public Slice<Review> getReviewList(ReviewReqDTO.GetReviewListDTO request) {

        // 한 번에 몇 개씩 가져올지 설정
        Pageable pageable = PageRequest.of(0, request.size());

        // 커서(lastReviewId) 존재 여부에 따라 다른 리포지토리 메서드 호출
        if (request.lastReviewId() == null) {
            // 처음 조회할 때는 그냥 최신순으로
            return reviewRepository.findAllByMemberIdOrderByIdDesc(
                    request.memberId(),
                    pageable
            );
        } else {
            // 두 번째부터는 마지막 ID보다 작은(과거) 데이터들을 가져옴
            return reviewRepository.findAllByMemberIdAndIdLessThanOrderByIdDesc(
                    request.memberId(),
                    request.lastReviewId(),
                    pageable
            );
        }
    }

    public ReviewResDTO.ReviewPreViewListDTO getReviewList(ReviewReqDTO.GetReviewListDTO request) {

        // 1. 페이징 설정 (사이즈는 DTO에서 가져옴)
        Pageable pageable = PageRequest.of(0, request.size());

        Slice<Review> reviewSlice;

        // 2. 레포지토리 호출 (커서 기반)
        if (request.lastReviewId() == null) {
            reviewSlice = reviewRepository.findAllByMemberIdOrderByIdDesc(request.memberId(), pageable);
        } else {
            reviewSlice = reviewRepository.findAllByMemberIdAndIdLessThanOrderByIdDesc(
                    request.memberId(), request.lastReviewId(), pageable);
        }

        // 3. [중요] 엔티티 리스트를 응답용 DTO로 변환해서 리턴!
        return ReviewConverter.toReviewPreViewListDTO(reviewSlice);
    }
}