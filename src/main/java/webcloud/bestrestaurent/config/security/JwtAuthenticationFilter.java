package webcloud.bestrestaurent.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationProvider jwtProvider;
    private RedisTemplate redisTemplate;

    public JwtAuthenticationFilter (JwtAuthenticationProvider jwtProvider, RedisTemplate redisTemplate) {
        this.jwtProvider = jwtProvider;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String accessToken = jwtProvider.resolveToken(request);
        if (accessToken != null && jwtProvider.validateToken(accessToken)) {

            String isLogout = (String)redisTemplate.opsForValue().get(accessToken);

            if (ObjectUtils.isEmpty(isLogout)) {
                Authentication authentication = jwtProvider.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

}