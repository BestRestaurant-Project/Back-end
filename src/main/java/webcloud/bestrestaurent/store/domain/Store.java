package webcloud.bestrestaurent.store.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import webcloud.bestrestaurent.user.domain.User;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "food_type")
    private String foodType;

    @Column(name = "rating_average")
    private Double ratingAverage;

    @Builder
    public Store(String name, String type, String foodType, Double ratingAverage){
        this.name = name;
        this.type = type;
        this.foodType = foodType;
        this.ratingAverage = ratingAverage;
    }

    public void setRatingAverage(Double ratingAverage) {
        this.ratingAverage = ratingAverage;
    }
}
