package umc._th.spring.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc._th.spring.domain.Mission;
import umc._th.spring.domain.enums.MissionStatus;

import java.util.List;

public interface MissionRepositoryCustom {
    List<Mission> findByMemberIdAndStatus(Long memberId, MissionStatus status);
    Page<Mission> findAllByRegion(Long memberId, Long regionId, Pageable pageable);
}
