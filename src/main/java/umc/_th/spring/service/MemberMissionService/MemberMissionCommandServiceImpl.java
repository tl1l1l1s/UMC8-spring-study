package umc._th.spring.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc._th.spring.apiPayload.code.status.ErrorStatus;
import umc._th.spring.apiPayload.exception.handler.MemberHandler;
import umc._th.spring.apiPayload.exception.handler.MissionHandler;
import umc._th.spring.domain.Member;
import umc._th.spring.domain.Mission;
import umc._th.spring.repository.MemberMissionRepository.MemberMissionRepository;
import umc._th.spring.repository.MemberRepository.MemberRepository;
import umc._th.spring.repository.MissionRepository.MissionRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    @Override
    public void startMission(Long memberId, Long missionId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        memberMissionRepository.startMission(member, mission);
    }
}
