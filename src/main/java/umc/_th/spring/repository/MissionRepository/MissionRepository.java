package umc._th.spring.repository.MissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc._th.spring.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {
}
