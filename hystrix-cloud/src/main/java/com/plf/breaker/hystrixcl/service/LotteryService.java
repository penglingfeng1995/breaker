package com.plf.breaker.hystrixcl.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author plf
 */
@Service
public class LotteryService {

    @Autowired
    private RestTemplate restTemplate;

    public String addDrawTime() {
        return restTemplate.getForObject("http://localhost:8081/score/addScore", String.class);
    }

    @HystrixCommand(fallbackMethod = "addDrawTimeFallback")
    public String addDrawTimeH() {
        return restTemplate.getForObject("http://localhost:8081/score/addScore", String.class);
    }

    public String addDrawTimeFallback() {
        return "保留记录，日后发放积分";
    }
}
