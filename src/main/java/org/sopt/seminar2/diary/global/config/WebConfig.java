package org.sopt.seminar2.diary.global.config;

import lombok.RequiredArgsConstructor;

import org.sopt.seminar2.diary.api.interceptor.CheckUserAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final CheckUserAuthInterceptor checkUserAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkUserAuthInterceptor);
    }
}
