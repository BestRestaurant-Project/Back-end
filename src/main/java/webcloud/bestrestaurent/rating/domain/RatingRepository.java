package webcloud.bestrestaurent.rating.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import webcloud.bestrestaurent.store.domain.Store;
import webcloud.bestrestaurent.user.domain.User;

import java.util.List;


public interface RatingRepository extends JpaRepository<Rating,Long> {
    Rating findByUserAndStoreId(User user,Long storeId);
    boolean existsByUserAndStoreId(User user,Long storeId);
    boolean existsByStoreId(Long storeId);
}
