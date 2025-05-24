package umc._th.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc._th.spring.domain.Review;
import umc._th.spring.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

    Boolean existsById(Long id);
    Optional<Store> findStore(Long id);
    List<Store> findStoreByNameAndScore(String name, Float score);
    Page<Review> getReviewList(Long storeId, Integer page);
}
