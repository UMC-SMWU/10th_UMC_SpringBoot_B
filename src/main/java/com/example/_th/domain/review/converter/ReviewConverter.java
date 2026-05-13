package com.example._th.domain.review.converter;


import com.example._th.domain.review.dto.ReviewResDTO;
import com.example._th.domain.review.entity.Review;
import org.springframework.data.domain.Slice;

import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResDTO.ReviewPreViewDTO toReviewPreViewDTO(Review review) {
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getNickname())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreViewListDTO(Slice<Review> reviewList) {
        List<ReviewResDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::toReviewPreViewDTO).collect(Collectors.toList());

        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())     // 다음 페이지가 있는지 여부
                .isFirst(reviewList.isFirst())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public class ReviewConverter {
        public static ReviewResDTO.ReviewPreViewListDTO toReviewPreViewListDTO(Slice<Review> reviewList) {

            // 리뷰 알맹이들만 추출해서 리스트로 만듦
            List<ReviewResDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                    .map(review -> ReviewResDTO.ReviewPreViewDTO.builder()
                            .ownerNickname(review.getMember().getNickname())
                            .score(review.getScore())
                            .body(review.getContent()) // DB 필드명에 맞게 수정 (content 혹은 body)
                            .createdAt(review.getCreatedAt().toLocalDate())
                            .build()
                    ).collect(Collectors.toList());

            // 최종 응답 객체 생성
            return ReviewResDTO.ReviewPreViewListDTO.builder()
                    .reviewList(reviewPreViewDTOList)
                    .isLast(reviewList.isLast()) // 다음 페이지가 더 있는지 알려줌 (중요!)
                    .build();
        }
    }
}