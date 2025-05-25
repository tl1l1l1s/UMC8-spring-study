package umc._th.spring.service.MemberMissionService;

import org.springframework.data.domain.Page;
import umc._th.spring.domain.mapping.MemberMission;

public interface MemberMissionQueryService {
    Page<MemberMission> findAllMemberMissionsByMemberId(Long memberId, Integer page);

}
