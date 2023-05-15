package webcloud.bestrestaurent.store.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import webcloud.bestrestaurent.store.domain.Store;

@Getter
@Setter
@NoArgsConstructor
public class GetStoreResponseDto {

    private Long storeId;
    private String name;
    private String type;
    private String foodType;
    private Double ratingAverage;

    public GetStoreResponseDto(Store store){
        this.storeId = store.getStoreId();
        this.name = store.getName();
        this.type = store.getType();
        this.foodType = store.getFoodType();
        this.ratingAverage = store.getRatingAverage();
    }

    @Builder
    public GetStoreResponseDto(Long storeId, String name, String type, String foodType, Double ratingAverage){
       this.storeId = storeId;
       this.name = name;
       this.type = type;
       this.foodType = foodType;
       this.ratingAverage = ratingAverage;

    }


}
