package webcloud.bestrestaurent.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .exposedHeaders("Access_Token")
                .allowCredentials(true)
                //.allowCredentials(true)과 .allowedOrigin("*") 같이 못쓰게 업데이트됨
                // .allowedOrigin("*") => .allowedOriginPatterns("*")
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods("*");
    }
}
