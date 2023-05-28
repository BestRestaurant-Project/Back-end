package webcloud.bestrestaurent.food.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import webcloud.bestrestaurent.ResponseDto;
import webcloud.bestrestaurent.food.request.InputFoodRequestDto;
import webcloud.bestrestaurent.food.service.FoodService;
import webcloud.bestrestaurent.reply.request.ReplyRequestDto;
import webcloud.bestrestaurent.reply.service.ReplyService;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodService foodService;

    @PostMapping("/food/input")
    public ResponseDto createReply(@Valid @RequestBody InputFoodRequestDto inputFoodRequestDto){
        return foodService.inputFood(inputFoodRequestDto);
    }

    @GetMapping("/foods/{storeId}/get")
    public ResponseDto getStores(@PathVariable Long storeId,
                                 @PageableDefault(size=10, sort="price", direction= Sort.Direction.DESC)
                                         Pageable pageable) {
        return foodService.getFoods(storeId, pageable);
    }

}
