package com.springboot.mycgv.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** class intro
 *
 * page list which should be checked before go to Controller
 * which means if client load page without session, client should have session value(=login) */




@Configuration
public class SessionConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new SessionAuthInterceptor());
//        interceptorRegistration.addPathPatterns("/mypage**/**", "admin**/**"); // page which should be checked session check
//        interceptorRegistration.excludePathPatterns(); // page which client can go without session check
        registry.addInterceptor(new SessionAuthInterceptor())
                .order(1)
                .addPathPatterns("/mypage**/**", "admin**/**")
                .excludePathPatterns();
    }



}
