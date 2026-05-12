package com.example.chap04.domain.review.Converter;

import com.example.chap04.domain.review.dto.ReviewResponseDTO;
import com.example.chap04.domain.review.entity.Reply;
import com.example.chap04.domain.review.entity.Review;
import com.example.chap04.domain.review.entity.ReviewPhoto;
import org.springframework.data.domain.Slice;

import java.util.List;

public class ReviewConverter {

    public static ReviewResponseDTO.ReviewInfoList toReviewInfoList(List<Review> reviews) {

        List<ReviewResponseDTO.ReviewInfo> reviewInfoList = reviews.stream()
                .map(ReviewConverter::toReviewInfo)
                .toList();

        return ReviewResponseDTO.ReviewInfoList.builder()
                .reviews(reviewInfoList)
                .build();
    }

    private static ReviewResponseDTO.ReviewInfo toReviewInfo(Review review) {

        List<ReviewResponseDTO.ReviewPhotoInfo> photoInfoList =
                toReviewPhotoInfoList(review.getReviewPhotos());

        ReviewResponseDTO.ReviewReplyInfo replyInfo =
                toReplyInfo(review.getReply());

        return ReviewResponseDTO.ReviewInfo.builder()
                .reviewId(review.getId())
                .memberId(review.getMember().getId())
                .nickname(review.getMember().getNickname())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .photos(photoInfoList)
                .reply(replyInfo)
                .build();
    }

    private static List<ReviewResponseDTO.ReviewPhotoInfo> toReviewPhotoInfoList(
            List<ReviewPhoto> reviewPhotos
    ) {
        return reviewPhotos.stream()
                .map(ReviewConverter::toReviewPhotoInfo)
                .toList();
    }

    private static ReviewResponseDTO.ReviewPhotoInfo toReviewPhotoInfo(ReviewPhoto reviewPhoto) {
        return ReviewResponseDTO.ReviewPhotoInfo.builder()
                .reviewPhotoId(reviewPhoto.getId())
                .photoUrl(reviewPhoto.getPhotoUrl())
                .build();
    }

    private static ReviewResponseDTO.ReviewReplyInfo toReplyInfo(Reply reply) {

        if (reply == null) {
            return null;
        }

        return ReviewResponseDTO.ReviewReplyInfo.builder()
                .replyId(reply.getId())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .build();
    }

    public static ReviewResponseDTO.MyReviewCursorResponse toMyReviewCursorResponse(
            Slice<Review> reviewSlice
    ) {
        List<ReviewResponseDTO.MyReviewDTO> reviewDTOList = reviewSlice.getContent()
                .stream()
                .map(ReviewConverter::toMyReviewDTO)
                .toList();

        Long nextCursorReviewId = null;
        Double nextCursorStar = null;

        if (!reviewDTOList.isEmpty()) {
            ReviewResponseDTO.MyReviewDTO lastReview =
                    reviewDTOList.get(reviewDTOList.size() - 1);

            nextCursorReviewId = lastReview.getReviewId();
            nextCursorStar = lastReview.getStar();
        }

        return ReviewResponseDTO.MyReviewCursorResponse.builder()
                .contents(reviewDTOList)
                .hasNext(reviewSlice.hasNext())
                .nextCursorReviewId(nextCursorReviewId)
                .nextCursorStar(nextCursorStar)
                .build();
    }

    private static ReviewResponseDTO.MyReviewDTO toMyReviewDTO(Review review) {
        return ReviewResponseDTO.MyReviewDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }
}