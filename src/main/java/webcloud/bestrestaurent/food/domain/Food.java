package webcloud.bestrestaurent.food.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import webcloud.bestrestaurent.store.domain.Store;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long foodId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "price")
    private Integer price;

    @Builder
    public Food(Long storeId, String foodName, Integer price){
        this.storeId = storeId;
        this.foodName = foodName;
        this.price = price;
    }

}
