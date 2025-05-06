package umc._th.spring.repository.ReviewRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc._th.spring.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
}
