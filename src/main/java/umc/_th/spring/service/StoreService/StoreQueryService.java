package umc._th.spring.service.StoreService;

import umc._th.spring.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    List<Store> findStoreByNameAndScore(String name, Float score);
}
