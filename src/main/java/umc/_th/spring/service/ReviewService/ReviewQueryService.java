package umc._th.spring.service.ReviewService;

import org.springframework.data.domain.Page;
import umc._th.spring.domain.Review;

public interface ReviewQueryService {
    Page<Review> getReviewList(Long memberID, Integer page);
}
