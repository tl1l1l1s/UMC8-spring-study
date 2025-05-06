package umc._th.spring.repository.StoreRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc._th.spring.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}
