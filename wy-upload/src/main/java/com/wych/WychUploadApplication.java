package com.wych;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class WychUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(WychUploadApplication.class,args);
    }
}
