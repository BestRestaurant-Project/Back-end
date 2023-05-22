package webcloud.bestrestaurent.rating.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class StarRatingRequestDto {
    private Long storeId;
    private Double ratingValue;

    @Builder
    public StarRatingRequestDto(Long storeId, Double ratingValue){
        this.storeId = storeId;
        this.ratingValue = ratingValue;
    }
}
