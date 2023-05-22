package webcloud.bestrestaurent.rating.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import webcloud.bestrestaurent.store.domain.Store;
import webcloud.bestrestaurent.user.domain.User;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long ratingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "rating_value")
    private Double ratingValue;

    @Builder
    public Rating(Long storeId, Double ratingValue){
        this.storeId = storeId;
        this.ratingValue = ratingValue;
    }

    public void insertUser(User user) {
        this.user = user;
    }
}
