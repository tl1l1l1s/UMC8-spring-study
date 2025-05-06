package umc._th.spring.repository.ReviewRepository;

import umc._th.spring.domain.Review;

public interface ReviewRepositoryCustom {
    Review addReview(Long memberId, Long storeId, String content, Float rate);
}
