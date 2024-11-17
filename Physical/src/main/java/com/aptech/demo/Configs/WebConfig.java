package com.aptech.demo.Configs;

import com.aptech.demo.Interceptors.AdminInterceptor;
import com.aptech.demo.Interceptors.ClientInterceptor;
import com.aptech.demo.Interceptors.LoginInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/login");
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin/login","/admin/chklogin");
        registry.addInterceptor(new ClientInterceptor()).addPathPatterns("/cart/**", "/check-out", "/lich-su-dat-hang", "/updateStatus/**",
               "/thong-tin-user", "/chi-tiet-don-hang/**", "/thay-doi-mat-khau", "/result", "/resultCOD").excludePathPatterns("/cart");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
