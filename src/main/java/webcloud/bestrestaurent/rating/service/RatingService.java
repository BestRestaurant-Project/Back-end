package webcloud.bestrestaurent.rating.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webcloud.bestrestaurent.ResponseDto;
import webcloud.bestrestaurent.config.security.JwtAuthenticationProvider;
import webcloud.bestrestaurent.rating.domain.Rating;
import webcloud.bestrestaurent.rating.domain.RatingRepository;
import webcloud.bestrestaurent.rating.request.StarRatingRequestDto;
import webcloud.bestrestaurent.store.domain.Store;
import webcloud.bestrestaurent.store.domain.StoreRepository;
import webcloud.bestrestaurent.user.domain.User;
import webcloud.bestrestaurent.user.domain.UserRepository;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final StoreRepository storeRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserDetailsService userDetailsService;


    public ResponseDto ratingStar(ServletRequest request, StarRatingRequestDto starRatingRequestDto){
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        System.out.println(token);
        //
        User user = (User)userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        Store store = storeRepository.findById(starRatingRequestDto.getStoreId())
                .orElseThrow(()-> new IllegalArgumentException("가게를 찾을 수 없습니다."));

        Rating rating;

        if(ratingRepository.existsByUserAndStoreId(user,store.getStoreId()) ){ // 이미 해당 가게에 별점을 매긴 유저라면
            //System.out.println(user.getUserId());
            //System.out.println(store.getStoreId());
            rating = ratingRepository.findByUserAndStoreId(user,store.getStoreId());
           // System.out.println(rating.getRatingId());
            store.setRatingTotal(store.getRatingTotal() - rating.getRatingValue() + starRatingRequestDto.getRatingValue());
            System.out.println(store.getRatingTotal());
            System.out.println(rating.getRatingValue());
            System.out.println(starRatingRequestDto.getRatingValue());
            System.out.println(store.getRatingTotal());
            // 가게의 총 별점 - 사용자가 저번에 매겼던 별점 + 사용자가 지금 매기는 별점
            store.setRatingAverage(Math.round( ((Double)(store.getRatingTotal() / store.getRatingNumber())) * 10 ) / 10.0);
            // 가게의 총 별점에 가게의 개수를 나누나 같은사람이므로 별점을 매기는 사람의 수는 그대로로 냅둠

            // 업데이트가 안되서 임시방편으로 삭제한 뒤 다시 생성..
            ratingRepository.deleteById(rating.getRatingId());
            rating = Rating.builder()
                    .storeId(starRatingRequestDto.getStoreId())
                    .ratingValue(starRatingRequestDto.getRatingValue())
                    .build();
            rating.insertUser(user);
            ratingRepository.save(rating);
            return new ResponseDto("SUCCESS",rating.getRatingId());
        }
        else { // 처음 별점을 매기는 유저라면
            store.setRatingTotal(store.getRatingTotal() + starRatingRequestDto.getRatingValue());
            store.setRatingNumber(store.getRatingNumber() + 1);
            store.setRatingAverage(Math.round( ((Double)(store.getRatingTotal() / store.getRatingNumber())) * 10 ) / 10.0);
            rating = Rating.builder()
                    .storeId(starRatingRequestDto.getStoreId())
                    .ratingValue(starRatingRequestDto.getRatingValue())
                    .build();
            rating.insertUser(user);
            ratingRepository.save(rating);

            return new ResponseDto("SUCCESS", rating.getRatingId());
        }
        // 한 사용자가 연속해서 별점을 보내게되면 문제가 있으니까..

        /*
                .ratingTotal(0D)
                .ratingAverage(0D)
                .ratingNumber(0)
         */

    }


}
