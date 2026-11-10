package com.baixiagu.takeout.infrastructure.wechat;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信配置项，mock=true 时不请求微信
 */
@Data
@Component
@ConfigurationProperties(prefix = "takeout.wechat")
public class WechatProperties {

    // TODO 按 application.yml 补字段
}
