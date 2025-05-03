package umc._th.spring.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc._th.spring.domain.Mission;
import umc._th.spring.domain.QMission;
import umc._th.spring.domain.enums.MissionStatus;
import umc._th.spring.domain.mapping.QMemberMission;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    private final QMission mission = QMission.mission;
    private final QMemberMission memberMission = QMemberMission.memberMission;

    @Override
    public List<Mission> findByMemberIdAndStatus(@NonNull Long memberId, @NonNull MissionStatus status) {
        BooleanBuilder predicate = new BooleanBuilder()
                .and(memberMission.member.id.eq(memberId))
                .and(memberMission.status.eq(status));

        return queryFactory.selectFrom(mission)
                .join(memberMission).on(mission.id.eq(memberMission.mission.id))
                .where(predicate)
                .fetch();
    }
}
