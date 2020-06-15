package com.gzhz.kcmqmonitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gzhz.kcmqmonitor.mapper")
public class KcmqmonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(KcmqmonitorApplication.class, args);
    }

}
