package umc._th.spring.repository.ReviewRepository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc._th.spring.domain.Review;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public Review addReview(Review review) {

        entityManager.persist(review);
        return review;
    }
}
