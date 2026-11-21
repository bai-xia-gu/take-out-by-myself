package com.baixiagu.takeout.config;

import com.baixiagu.takeout.auth.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器注册、跨域、上传文件静态映射
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        // 免鉴权（接口文档 1.2）
                        "/admin/auth/login",
                        "/user/auth/login",
                        "/categories",
                        "/shops", "/shops/**",
                        "/products/**",
                        "/search/**",
                        // 框架与自检
                        "/ping", "/error",
                        "/doc.html", "/webjars/**", "/v3/api-docs/**",
                        "/swagger-ui/**", "/swagger-resources/**",
                        "/static/**"
                );

    }

    // 上传文件静态映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("file:./upload/");

    }

    /**
     * 跨域配置 说人话就是 允许所有来源访问所有方法、所有头、允许凭证、缓存时间 1 小时
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
