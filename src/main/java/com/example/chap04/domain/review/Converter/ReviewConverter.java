package com.example.chap04.domain.review.Converter;

import com.example.chap04.domain.review.dto.ReviewResponseDTO;
import com.example.chap04.domain.review.entity.Reply;
import com.example.chap04.domain.review.entity.Review;
import com.example.chap04.domain.review.entity.ReviewPhoto;

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
}