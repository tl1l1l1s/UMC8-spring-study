package umc._th.spring.repository.RegionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc._th.spring.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long>, RegionRepositoryCustom {
}
