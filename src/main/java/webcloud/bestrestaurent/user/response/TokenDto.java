package webcloud.bestrestaurent.user.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TokenDto {
    private String accessToken;
    private String refreshToken;
    private Long accessTokenTime;
    private Long refreshTokenTime;
}