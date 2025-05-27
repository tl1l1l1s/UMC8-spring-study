package umc._th.spring.repository.MemberMissionRepository;

import umc._th.spring.domain.Member;
import umc._th.spring.domain.Mission;

public interface MemberMissionRepositoryCustom {
    void startMission(Member member, Mission mission);
    void endMission(Member member, Mission mission);
}
