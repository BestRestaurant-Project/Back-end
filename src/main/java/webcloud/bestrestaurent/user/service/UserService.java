package webcloud.bestrestaurent.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webcloud.bestrestaurent.ResponseDto;
import webcloud.bestrestaurent.config.security.JwtAuthenticationProvider;
import webcloud.bestrestaurent.user.domain.User;
import webcloud.bestrestaurent.user.domain.UserRepository;
import webcloud.bestrestaurent.user.request.SignInRequestDto;
import webcloud.bestrestaurent.user.request.SignUpRequestDto;
import webcloud.bestrestaurent.user.response.LoginDto;
import webcloud.bestrestaurent.user.response.TokenDto;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate redisTemplate;

    @Transactional
    public ResponseDto userSignUp(SignUpRequestDto signUpRequestDto) {
        if(userRepository.existsByUserName(signUpRequestDto.getUserName())){
            return new ResponseDto("FAIL","이미 가입되어있는 회원입니다.");
        }
        User user = User.builder()
                .userName(signUpRequestDto.getUserName())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .build();
        userRepository.save(user);

        return new ResponseDto("SUCCESS",user.getUserId());
    }
    public ResponseDto checkUserName(String userName) {
        boolean result = userRepository.existsByUserName(userName);
        if (result) {
            return new ResponseDto("FAIL", "이미 존재하는 유저네임입니다.");
        }
        return new ResponseDto("SUCCESS", "사용가능한 유저네임입니다.");
    }

    public ResponseDto userSignIn(SignInRequestDto signInRequestDto) {
        if (!userRepository.existsByUserName(signInRequestDto.getUserName())) {
            return new ResponseDto("FAIL", "존재하지 않는 유저네임입니다.");}

        User user = userRepository.findByUserName(signInRequestDto.getUserName());
        if (!passwordEncoder.matches(signInRequestDto.getPassword(), user.getPassword())) {
            return new ResponseDto("FAIL", "비밀번호가 틀렸습니다.");
        }
        // 로그인 할 경우 "AccessToken"과 "RefreshToken"을 "TokenDto"에 넣어 반환
        TokenDto tokenDto = jwtAuthenticationProvider.createToken(user.getUsername(), user.getRoles());

        // 생성된 "RefreshToken"를 "Redis"에 저장, 시간이 만료 되면 자동적으로 삭제
        // key 값: "RT:email",
        redisTemplate.opsForValue().set("RT:"+user.getUsername(),
                tokenDto.getRefreshToken(), tokenDto.getRefreshTokenTime(), TimeUnit.MILLISECONDS);

        LoginDto loginDto = new LoginDto();
        loginDto.setUserId(user.getUserId());
        loginDto.setTokenDto(tokenDto);
        // login시 userid 넘겨주기 위함
        return new ResponseDto("SUCCESS",loginDto);

    }
}
