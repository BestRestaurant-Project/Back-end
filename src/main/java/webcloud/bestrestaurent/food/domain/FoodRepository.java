package webcloud.bestrestaurent.food.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,Long> {

    List<Food> findByStoreId(Long storeId, Pageable pageable);
}
