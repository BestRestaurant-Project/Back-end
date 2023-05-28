package webcloud.bestrestaurent.food.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webcloud.bestrestaurent.ResponseDto;
import webcloud.bestrestaurent.food.domain.Food;
import webcloud.bestrestaurent.food.domain.FoodRepository;
import webcloud.bestrestaurent.food.request.InputFoodRequestDto;
import webcloud.bestrestaurent.food.response.GetFoodResponseDto;
import webcloud.bestrestaurent.store.response.GetStoreResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public ResponseDto inputFood(InputFoodRequestDto inputFoodRequestDto){

        Food food = Food.builder()
                .storeId(inputFoodRequestDto.getStoreId())
                .foodName(inputFoodRequestDto.getFoodName())
                .price(inputFoodRequestDto.getPrice())
                .build();

        foodRepository.save(food);

        return new ResponseDto("SUCCESS", food.getFoodId());
    }

    public ResponseDto getFoods(Long storeId, Pageable pageable) {

        List<GetFoodResponseDto> pages = foodRepository.findByStoreId(storeId, pageable)
                .stream()
                .map(food -> new GetFoodResponseDto(food))
                .collect(Collectors.toList());

        return new ResponseDto("SUCCESS",pages);

    }

}
