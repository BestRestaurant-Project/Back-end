package webcloud.bestrestaurent.store.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Store> getStores(int store) {
        return jpaQueryFactory.selectFrom(QStore.store)
                .limit(30)
                .offset((long) (store -1) * 5)
                .orderBy(QStore.store.ratingAverage.desc())
                .fetch();
    }
}
