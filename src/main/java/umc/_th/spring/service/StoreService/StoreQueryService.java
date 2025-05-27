package umc._th.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc._th.spring.domain.Review;
import umc._th.spring.domain.Store;
import umc._th.spring.web.dto.MissionResponseDTO;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    List<Store> findStoreByNameAndScore(String name, Float score);
    MissionResponseDTO.MissionPreViewListDTO getMissionList(Long storeId, Integer page);
    Page<Review> getReviewList(Long storeId, Integer page);
}
