package umc._th.spring.service.MissionService;

import umc._th.spring.domain.Mission;
import umc._th.spring.web.dto.MissionRequestDTO;

public interface MissionCommandService {
    Mission createMission(Long storeId, MissionRequestDTO.AddMissionDTO request);
}
