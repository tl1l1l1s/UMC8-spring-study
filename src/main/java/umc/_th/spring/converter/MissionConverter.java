package umc._th.spring.converter;

import umc._th.spring.domain.Mission;
import umc._th.spring.domain.Store;
import umc._th.spring.web.dto.MissionRequestDTO;
import umc._th.spring.web.dto.MissionResponseDTO;

public class MissionConverter {

    public static MissionResponseDTO.AddMissionResultDTO toAddMissionResultDTO(Mission mission) {
        return MissionResponseDTO.AddMissionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static Mission toMission(Store store, MissionRequestDTO.AddMissionDTO request) {
        return Mission.builder()
                .point(request.getPoint())
                .reward(request.getReward())
                .store(store)
                .build();
    }
}
