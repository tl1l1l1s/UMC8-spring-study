package umc._th.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc._th.spring.apiPayload.code.status.ErrorStatus;
import umc._th.spring.apiPayload.exception.handler.StoreHandler;
import umc._th.spring.converter.MissionConverter;
import umc._th.spring.domain.Mission;
import umc._th.spring.domain.Store;
import umc._th.spring.repository.MissionRepository.MissionRepository;
import umc._th.spring.repository.StoreRepository.StoreRepository;
import umc._th.spring.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public Mission createMission(Long storeId, MissionRequestDTO.AddMissionDTO request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        return missionRepository.addMission(MissionConverter.toMission(store, request));
    }
}
