package com.baixiagu.takeout.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器注册、跨域、上传文件静态映射
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // TODO 注册 AuthInterceptor、跨域、/files/** 静态映射
}
