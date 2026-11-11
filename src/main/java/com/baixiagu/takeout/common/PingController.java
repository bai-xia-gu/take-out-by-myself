package com.baixiagu.takeout.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;


//这是服务的"心跳接口"，用来确认你的后端是否活着、是否正常响应请求。
@RestController
public class PingController {

    @GetMapping("/ping")
    public Result<Map<String, Object>> ping() {
        return Result.ok(Map.of(
                "status", "ok",
                "time", LocalDateTime.now()
        ));
    }
}