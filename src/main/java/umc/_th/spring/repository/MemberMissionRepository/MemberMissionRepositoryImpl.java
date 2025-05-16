package umc._th.spring.repository.MemberMissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc._th.spring.apiPayload.code.status.ErrorStatus;
import umc._th.spring.apiPayload.exception.handler.MissionHandler;
import umc._th.spring.domain.Member;
import umc._th.spring.domain.Mission;
import umc._th.spring.domain.enums.MissionStatus;
import umc._th.spring.domain.mapping.MemberMission;
import umc._th.spring.domain.mapping.QMemberMission;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom {

    private final EntityManager entityManager;
    private final JPAQueryFactory jpaQueryFactory;
    private final QMemberMission memberMission = QMemberMission.memberMission;

    @Override
    public void startMission(Member member, Mission mission) {
        BooleanBuilder predicate = new BooleanBuilder()
                .and(memberMission.member.id.eq(member.getId()))
                .and(memberMission.mission.id.eq(mission.getId()))
                .and(memberMission.status.eq(MissionStatus.CHALLENGING));

        List<MemberMission> checkExists = jpaQueryFactory.selectFrom(memberMission)
                .where(predicate)
                .fetch();
        if(!checkExists.isEmpty()) {
            throw new MissionHandler(ErrorStatus.ALREADY_CHALLENGING_MISSION);
        }

        // challenging 중인 미션이 아닌 경우
        entityManager.persist(MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build());
    }
}
