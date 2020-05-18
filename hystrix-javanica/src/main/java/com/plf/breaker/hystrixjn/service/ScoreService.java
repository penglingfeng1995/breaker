package com.plf.breaker.hystrixjn.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ScoreService {
    @Autowired
    private RestTemplate restTemplate;
    public String addScore() {
        return restTemplate.getForObject("http://localhost:8081/score/addScore", String.class);
    }

    @HystrixCommand(fallbackMethod = "addScoreFallback")
    public String addScoreH() {
        return restTemplate.getForObject("http://localhost:8081/score/addScore", String.class);
    }

    public String addScoreFallback() {
        return "保留记录，日后发放积分";
    }
}
