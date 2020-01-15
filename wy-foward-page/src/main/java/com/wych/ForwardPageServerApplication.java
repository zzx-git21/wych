package com.wych;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ForwardPageServerApplication {
    public static void main(String[] args) {
        SpringApplication.run (ForwardPageServerApplication.class,args);
    }
}
