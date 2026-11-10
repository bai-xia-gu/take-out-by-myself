package com.baixiagu.takeout.infrastructure.llm;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * DeepSeek 配置项
 */
@Data
@Component
@ConfigurationProperties(prefix = "takeout.ai.deepseek")
public class DeepSeekProperties {

    // TODO 按 application.yml 补字段
}
