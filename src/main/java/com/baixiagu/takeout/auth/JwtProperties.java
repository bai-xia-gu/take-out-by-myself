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
    private String secret = "zhisu-waimai-local-dev-secret-key-0123456789";

    private int expireDays = 7;

    public long getExpireMillis() {
        return (long) expireDays * 24 * 60 * 60;
    }
}
