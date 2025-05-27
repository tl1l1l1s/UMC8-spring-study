package umc._th.spring.service.MemberMissionService;

public interface MemberMissionCommandService {
    void startMission(Long memberId, Long missionId);
    void completeMission(Long missionId, Long memberId);
}
