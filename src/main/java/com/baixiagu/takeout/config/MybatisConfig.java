package com.baixiagu.takeout.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis 配置：Mapper 接口扫描，SQL 写在 resources/mapper/*.xml
 */
@Configuration
@MapperScan("com.baixiagu.takeout.**.mapper")
public class MybatisConfig {
}
