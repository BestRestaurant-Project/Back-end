package webcloud.bestrestaurent.user.request;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor // 파라미터가 없는 기본 생성자 생성 어노테이션
public class SignUpRequestDto {

    @NotBlank(message = "유저네임을 입력해주세요")
    private String userName;

    @Size(min=0,max=8, message = "0~8 글자의 패스워드를 입력해주세요")
    private String password;

    @Builder
    public SignUpRequestDto(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
}
