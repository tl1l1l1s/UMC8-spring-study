package umc._th.spring.service.MissionService;

import umc._th.spring.domain.Mission;
import umc._th.spring.domain.enums.MissionStatus;

import java.util.List;

public interface MissionQueryService {
    List<Mission> findMissionByMemberIdAndStatus(Long memberId, MissionStatus status);
}
