package webcloud.bestrestaurent.store.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store,Long>, StoreRepositoryCustom {
    // 페이지네이션 :  한번에 모든 데이터를 요청하는 게 아니라 사용자가 볼 만큼만 서버에
    // 요청하면서 서버에 부하를 줄이도록 하는 기술
    List<Store> findByType(String type, Pageable pageable);
}
