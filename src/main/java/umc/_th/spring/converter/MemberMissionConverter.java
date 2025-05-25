package umc._th.spring.converter;

import org.springframework.data.domain.Page;
import umc._th.spring.domain.Mission;
import umc._th.spring.domain.Store;
import umc._th.spring.domain.mapping.MemberMission;
import umc._th.spring.web.dto.MemberMissionResponseDTO;

import java.util.List;

public class MemberMissionConverter {

    public static MemberMissionResponseDTO.MemberMissionPreViewDTO toMemberMissionPreViewDTO(MemberMission memberMission) {
        Mission mission = memberMission.getMission();
        Store store = mission.getStore();

        return MemberMissionResponseDTO.MemberMissionPreViewDTO.builder()
                .storeName(store.getName())
                .reward(mission.getReward())
                .point(mission.getPoint())
                .build();
    }

    public static MemberMissionResponseDTO.MemberMissionPreViewListDTO toMemberMissionPreViewListDTO (Page<MemberMission> memberMissionList) {
        List<MemberMissionResponseDTO.MemberMissionPreViewDTO> memberMissionPreViewDTOList = memberMissionList.stream()
                .map(MemberMissionConverter::toMemberMissionPreViewDTO).toList();

        return MemberMissionResponseDTO.MemberMissionPreViewListDTO.builder()
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(memberMissionPreViewDTOList.size())
                .missionList(memberMissionPreViewDTOList)
                .build();
    }
}
