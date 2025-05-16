package umc._th.spring.service.ReviewService;


import umc._th.spring.domain.Review;
import umc._th.spring.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {
    Review createReview(Long memberId, Long storeId, ReviewRequestDTO.AddReviewDTO request);
}
