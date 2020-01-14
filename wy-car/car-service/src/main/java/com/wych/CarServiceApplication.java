package com.wych;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.wych.mapper")
public class CarServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarServiceApplication.class,args);
    }

}
