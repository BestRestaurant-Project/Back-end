package webcloud.bestrestaurent.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import webcloud.bestrestaurent.ResponseDto;
import webcloud.bestrestaurent.user.request.SignInRequestDto;
import webcloud.bestrestaurent.user.request.SignUpRequestDto;
import webcloud.bestrestaurent.user.service.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseDto userSignUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto){
        return userService.userSignUp(signUpRequestDto);
    }

    @PostMapping("/signIn") // "RequestBody"에 유효성 검증하기 위해서 "@Valid" 입력
    public ResponseDto userSingIn(@Valid @RequestBody SignInRequestDto signInRequestDto){
        return userService.userSignIn(signInRequestDto);
    }

    @GetMapping("check/userName/{userName}") // 회원가입 시 작성한 닉네임이 데이터베이스에 중복되어 있으면 회원가입 불가
    // 여기서 유저네임은 웹사이트내에서 사용하는 닉네임 개념(로그인 시에는 유저 네임, 비밀번호 사용!)
    public ResponseDto checkUserName(@PathVariable String userName){
        return userService.checkUserName(userName);
    }
}
