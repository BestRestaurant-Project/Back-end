package webcloud.bestrestaurent.store.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import webcloud.bestrestaurent.ResponseDto;
import webcloud.bestrestaurent.config.security.JwtAuthenticationProvider;
import webcloud.bestrestaurent.store.domain.Store;
import webcloud.bestrestaurent.store.domain.StoreRepository;
import webcloud.bestrestaurent.store.request.CreateStoreRequestDto;
import webcloud.bestrestaurent.store.response.GetStoreResponseDto;
import webcloud.bestrestaurent.user.domain.User;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StoreService {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserDetailsService userDetailsService;
    private final StoreRepository storeRepository;

    @Transactional
    public ResponseDto createStore(CreateStoreRequestDto createStoreRequestDto) {
        Store store = Store.builder()
                .name(createStoreRequestDto.getName())
                .type(createStoreRequestDto.getType())
                .foodType(createStoreRequestDto.getFoodType())
                .build();

        store.setRatingAverage(0D); // 처음 가게 생성 시 별점은 0

        storeRepository.save(store);

        return new ResponseDto("SUCCESS", store.getStoreId());

    }

    public ResponseDto getStore(Long storeId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(()-> new IllegalArgumentException("가게를 찾을 수 없습니다."));

        GetStoreResponseDto getStoreResponseDto = GetStoreResponseDto.builder()
                .storeId(store.getStoreId())
                .name(store.getName())
                .type(store.getType())
                .foodType(store.getFoodType())
                .ratingAverage(store.getRatingAverage())
                .build();

        return new ResponseDto("SUCCESS", getStoreResponseDto);
    }


    public ResponseDto getStores(String type, Pageable pageable) {

        List<GetStoreResponseDto> pages = storeRepository.findByType(type, pageable)
                .stream()
                .map(post -> new GetStoreResponseDto(post))
                .collect(Collectors.toList());

        return new ResponseDto("SUCCESS",pages);

    }
}
