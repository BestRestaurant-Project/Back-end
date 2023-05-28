package webcloud.bestrestaurent.food.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputFoodRequestDto {

    private Long storeId;

    private String foodName;

    private Integer price;
}
