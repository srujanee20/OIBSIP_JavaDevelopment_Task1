package io.valkycodes.trs.config;

import io.valkycodes.trs.interceptor.AuthInterceptor;
import io.valkycodes.trs.interceptor.GuestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private GuestInterceptor guestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/",
                        "/login",
                        "/register",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/h2-console/**",
                        "/error"
                );

        registry.addInterceptor(guestInterceptor)
                .addPathPatterns("/", "/login", "/register");
    }
}
