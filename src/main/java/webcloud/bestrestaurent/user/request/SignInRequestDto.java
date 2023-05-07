package webcloud.bestrestaurent.user.request;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class SignInRequestDto {

    @Size(min=0,max=8, message = "0~8 글자의 유저네임을 입력해주세요")
    private String userName;

    @Size(min=0,max=8, message = "0~8 글자의 패스워드를 입력해주세요")
    private String password;

    @Builder
    public SignInRequestDto(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
}
