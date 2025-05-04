package umc._th.spring.service.ReviewService;


import umc._th.spring.domain.Review;

public interface ReviewCommandService {
    Review createReview(Long memberId, Long storeId, String content, Float rate);
}
