package com.baixiagu.takeout.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT 配置项
 */
@Data
@Component
@ConfigurationProperties(prefix = "takeout.jwt")
public class JwtProperties {

    // TODO 按 application.yml 补字段
}
