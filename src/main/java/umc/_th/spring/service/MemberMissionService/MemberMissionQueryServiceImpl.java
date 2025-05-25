package umc._th.spring.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc._th.spring.domain.mapping.MemberMission;
import umc._th.spring.repository.MemberMissionRepository.MemberMissionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Page<MemberMission> findAllMemberMissionsByMemberId(Long memberId, Integer page) {
        return memberMissionRepository.findAllByMemberId(memberId, PageRequest.of(page-1, 10));
    }
}
