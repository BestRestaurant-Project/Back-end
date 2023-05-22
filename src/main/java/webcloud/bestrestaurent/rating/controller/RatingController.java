package webcloud.bestrestaurent.rating.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import webcloud.bestrestaurent.ResponseDto;
import webcloud.bestrestaurent.rating.request.StarRatingRequestDto;
import webcloud.bestrestaurent.rating.service.RatingService;
import webcloud.bestrestaurent.store.request.CreateStoreRequestDto;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RatingController {
    private final RatingService ratingService;

    @PostMapping("/starrating/evaluate")
    public ResponseDto ratingStar(ServletRequest request, @Valid @RequestBody StarRatingRequestDto starRatingRequestDto){
        return ratingService.ratingStar(request, starRatingRequestDto);
    }


}
