package umc._th.spring.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc._th.spring.domain.Mission;
import umc._th.spring.domain.enums.MissionStatus;

public interface MissionRepositoryCustom {
    Page<Mission> findAllByMemberIdAndStatus(Long memberId, MissionStatus status, Pageable pageable);
    Page<Mission> findAllByRegion(Long memberId, Long regionId, Pageable pageable);
}
