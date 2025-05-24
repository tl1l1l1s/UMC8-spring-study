package umc._th.spring.converter;

import umc._th.spring.domain.Member;
import umc._th.spring.domain.Review;
import umc._th.spring.domain.Store;
import umc._th.spring.web.dto.ReviewRequestDTO;
import umc._th.spring.web.dto.ReviewResponseDTO;

public class ReviewConverter {

    public static ReviewResponseDTO.AddReviewResultDTO toAddReviewResultDTO(Review review) {

        return ReviewResponseDTO.AddReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static Review toReview(Member member, Store store, ReviewRequestDTO.AddReviewDTO request) {

        return Review.builder()
                .rate(request.getRate())
                .content(request.getContent())
                .member(member)
                .store(store)
                .build();
    }
}
