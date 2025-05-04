package umc._th.spring.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc._th.spring.domain.Mission;
import umc._th.spring.domain.QMission;
import umc._th.spring.domain.QStore;
import umc._th.spring.domain.enums.MissionStatus;
import umc._th.spring.domain.mapping.QMemberMission;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    private final QMission mission = QMission.mission;
    private final QMemberMission memberMission = QMemberMission.memberMission;
    private final QStore store = QStore.store;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Mission> findAllByMemberIdAndStatus(@NonNull Long memberId, @NonNull MissionStatus status, Pageable pageable) {
        BooleanBuilder predicate = new BooleanBuilder()
                .and(memberMission.member.id.eq(memberId))
                .and(memberMission.status.eq(status));

        List<Mission> missionList = queryFactory.selectFrom(mission)
                .join(memberMission).on(mission.id.eq(memberMission.mission.id))
                .where(predicate)
                .fetch();

        long total = jpaQueryFactory
                .select(mission.count())
                .from(mission)
                .where(predicate)
                .fetchOne();

        return new PageImpl<>(missionList, pageable, total);
    }

    @Override
    public Page<Mission> findAllByRegion(Long memberId, Long regionId, Pageable pageable) {
        BooleanBuilder predicate = new BooleanBuilder()
                .and(mission.store.region.id.eq(regionId))
                .and(mission.expiredAt.gt(LocalDateTime.now()));

        if(memberId != null) {
            predicate.and(mission.id.notIn(
                    JPAExpressions
                            .select(memberMission.mission.id)
                            .from(memberMission)
                            .where(memberMission.member.id.eq(memberId)
                            .and(memberMission.status.eq(MissionStatus.CHALLENGING)))
            ));
        }

        List<Mission> missionList = jpaQueryFactory.selectFrom(mission)
                .leftJoin(mission.store, store).fetchJoin() // fetch join을 사용하여 store 데이터를 한 번에 로딩
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory // count()를 사용하여 효율적으로 카운트를 처리
                .select(mission.count())
                .from(mission)
                .where(predicate)
                .fetchOne();

        return new PageImpl<>(missionList, pageable, total);
    }
}
