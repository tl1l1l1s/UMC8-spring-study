package umc._th.spring.repository.StoreRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc._th.spring.domain.QStore;
import umc._th.spring.domain.Store;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    private final QStore store = QStore.store;

    @Override
    public List<Store> dynamicQueryWithBooleanBuilder(String name, Float score) {
        BooleanBuilder predicate = new BooleanBuilder();

        if(name != null) {
            predicate.and(store.name.eq(name));
        }

        if(score != null) {
            predicate.and(store.score.goe(score));
        }

        return queryFactory.selectFrom(store)
                .where(predicate)
                .fetch();
    }
}
