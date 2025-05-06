package umc._th.spring.repository.ReviewRepository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc._th.spring.domain.Member;
import umc._th.spring.domain.Review;
import umc._th.spring.domain.Store;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public Review addReview(Long memberId, Long storeId, String content, Float rate) {
        // 영속성 컨텍스트로 객체 관계를 처리하기 위해 entity Manager 사용
            // 엔티티를 메모리에 로드하기 때문에 대량 데이터 처리 시에는 비효율적일 수 있음
        Member member = entityManager.getReference(Member.class, memberId);
        Store store = entityManager.getReference(Store.class, storeId);
            // getReference를 사용하여 실제 데이터베이스 조회를 피하고, 프록시를 사용하도록 유도

        Review review = Review.builder() // 빌더 패턴을 사용하여 객체 생성
                .member(member)
                .store(store)
                .content(content)
                .rate(rate)
                .build();

        entityManager.persist(review); // 영속화
        return review;
    }
}
