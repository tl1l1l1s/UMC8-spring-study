package umc._th.spring.repository.StoreRepository;

import umc._th.spring.domain.Store;
import java.util.List;


public interface StoreRepositoryCustom {
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
}