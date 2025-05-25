package umc._th.spring.converter;

import org.springframework.data.domain.Page;
import umc._th.spring.domain.Mission;
import umc._th.spring.domain.Store;
import umc._th.spring.web.dto.MissionRequestDTO;
import umc._th.spring.web.dto.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResponseDTO.MissionPreViewDTO toMissionPreviewDTO(Mission mission) {
        return MissionResponseDTO.MissionPreViewDTO.builder()
                .reward(mission.getReward())
                .point(mission.getPoint())
                .build();
    }

    public static MissionResponseDTO.MissionPreViewListDTO toMissionPreViewListDTO(Page<Mission> missionList, Store store) {
        List<MissionResponseDTO.MissionPreViewDTO> missionPreViewList = missionList.stream()
                .map(MissionConverter::toMissionPreviewDTO).collect(Collectors.toList());

        return MissionResponseDTO.MissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewList.size())
                .missionList(missionPreViewList)
                .storeName(store.getName())
                .build();
    }

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
