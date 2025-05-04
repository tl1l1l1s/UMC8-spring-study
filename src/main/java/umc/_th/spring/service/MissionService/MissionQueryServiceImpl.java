package umc._th.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc._th.spring.domain.Mission;
import umc._th.spring.domain.enums.MissionStatus;
import umc._th.spring.repository.MissionRepository.MissionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public Page<Mission> findAllMissionsByMemberIdAndStatus(Long memberId, MissionStatus status, Pageable pageable) {
        return missionRepository.findAllByMemberIdAndStatus(memberId, status, pageable);
    }

    @Override
    public Page<Mission> findAllMissionsByRegion(Long memberId, Long regionId, Pageable pageable) {
        return missionRepository.findAllByRegion(memberId, regionId, pageable);
    }
}
