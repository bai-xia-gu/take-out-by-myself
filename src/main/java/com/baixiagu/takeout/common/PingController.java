package com.baixiagu.takeout.common;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务存活检查
 */
@Tag(name = "自检")
@RestController
@RequestMapping("/ping")
public class PingController {

    // TODO 按接口契约逐步实现
}
