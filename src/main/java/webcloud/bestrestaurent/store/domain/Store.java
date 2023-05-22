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

    @Column(name = "rating_total")
    private Double ratingTotal;

    @Column(name = "rating_average")
    private Double ratingAverage;

    @Column(name = "ratingNumber") // 별점을 준 사용자 수
    private Integer ratingNumber;

    @Builder
    public Store(String name, String type, String foodType,Double ratingTotal, Double ratingAverage, Integer ratingNumber){
        this.name = name;
        this.type = type;
        this.foodType = foodType;
        this.ratingTotal = ratingTotal;
        this.ratingAverage = ratingAverage;
        this.ratingNumber = ratingNumber;
    }

    public void setRatingTotal(Double ratingTotal) { this.ratingTotal = ratingTotal; }
    public void setRatingAverage(Double ratingAverage) {
        this.ratingAverage = ratingAverage;
    }
    public void setRatingNumber(Integer ratingNumber) { this.ratingNumber = ratingNumber; }
}
