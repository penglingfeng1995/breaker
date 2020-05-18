package com.plf.breaker.hystrixcl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author penglf3
 */
@SpringBootApplication
@EnableHystrix
public class HystrixClApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixClApplication.class,args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
