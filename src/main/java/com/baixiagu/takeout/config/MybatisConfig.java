package com.baixiagu.takeout.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/** 扫到所有 xxx.mapper 包下的接口，省得每个都写 @Mapper */
@Configuration
@MapperScan("com.baixiagu.takeout.**.mapper")
public class MybatisConfig {
}