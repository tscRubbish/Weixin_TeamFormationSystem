package com.example.weixin.config;

import com.example.weixin.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author qin
 * @date 2020-06-22
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
//    @Autowired
//    JwtInterceptor jwtInterceptor;

    @Bean
    public JwtInterceptor authInterceptor(){
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/user/login")
                .excludePathPatterns("/api/user/register")
                .excludePathPatterns("/api/**/getInfo")
                .excludePathPatterns("/api/**/search");
    }
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").exposedHeaders("nju-token");
//    }
}
