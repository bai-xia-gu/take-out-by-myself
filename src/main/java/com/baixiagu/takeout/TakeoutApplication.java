package com.baixiagu.takeout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

/**
 * 智速外卖后端启动类
 */
@SpringBootApplication
public class TakeoutApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(TakeoutApplication.class, args);
    }
}
