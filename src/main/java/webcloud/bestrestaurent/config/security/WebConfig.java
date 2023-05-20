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
                .allowedOrigins("http://localhost:3000","http://172.31.42.213:3000")
                .allowedHeaders("*")
                .allowedMethods("*");
    }
}
