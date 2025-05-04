package umc._th.spring.service.MissionService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc._th.spring.domain.Mission;
import umc._th.spring.domain.enums.MissionStatus;

import java.util.List;

public interface MissionQueryService {
    List<Mission> findAllMissionsByMemberIdAndStatus(Long memberId, MissionStatus status);
    Page<Mission> findAllMissionsByRegion(Long memberId, Long regionId, Pageable pageable);
}
