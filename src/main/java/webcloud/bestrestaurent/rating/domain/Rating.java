package webcloud.bestrestaurent.rating.domain;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "rating")
    private Double rating;

    public void insertUser(User user) {
        this.user = user;
    }

    public void insertStore(Store store) {
        this.store = store;
    }
}
