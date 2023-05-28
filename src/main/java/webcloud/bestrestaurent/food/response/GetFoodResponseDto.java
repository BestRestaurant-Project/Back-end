package webcloud.bestrestaurent.food.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import webcloud.bestrestaurent.food.domain.Food;

@Getter
@Setter
public class GetFoodResponseDto {
    private Long foodId;
    private Long storeId;
    private String foodName;
    private Integer price;

    public GetFoodResponseDto(Food food){
        this.foodId = food.getFoodId();
        this.storeId = food.getStoreId();
        this.foodName = food.getFoodName();
        this.price = food.getPrice();
    }

    @Builder
    public GetFoodResponseDto(Long foodId, Long storeId,String foodName, Integer price){
        this.foodId = foodId;
        this.storeId = storeId;
        this.foodName = foodName;
        this.price = price;
    }
}
