package webcloud.bestrestaurent.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import webcloud.bestrestaurent.ResponseDto;
import webcloud.bestrestaurent.store.request.CreateStoreRequestDto;
import webcloud.bestrestaurent.store.service.StoreService;


import javax.servlet.ServletRequest;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/store/create")
    public ResponseDto createStore(@Valid @RequestBody CreateStoreRequestDto createStoreRequestDto){
        return storeService.createStore(createStoreRequestDto);
    }

    @GetMapping("/stores/{type}/get")
    public ResponseDto getStores(ServletRequest request, @PathVariable String type,
                                 @PageableDefault(size=50, sort="ratingAverage", direction= Sort.Direction.DESC)
                                         Pageable pageable) {
        return storeService.getStores(request, type, pageable);
    }


    @GetMapping("/store/{storeId}/get")
    public ResponseDto getStore(ServletRequest request, @PathVariable Long storeId){
        return storeService.getStore(request, storeId);
    }

}
