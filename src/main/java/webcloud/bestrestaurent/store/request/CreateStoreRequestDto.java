package webcloud.bestrestaurent.store.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStoreRequestDto {

    private String name;
    private String type;
    private String foodType;
}
