package com.plf.breaker.hystrixjn.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author penglf3
 */
@Slf4j
@Service
public class RegisterService {
    @Autowired
    private RestTemplate restTemplate;

    public String register() {
        log.info("注册成功");
        String scoreResult = addScore();
        log.info("赠送积分响应:{}", scoreResult);
        return "success" + scoreResult;
    }

    public String addScore() {
        return restTemplate.getForObject("http://localhost:8081/score/addScore", String.class);
    }

    public String registerH() {
        log.info("注册成功");
        String scoreResult = addScoreH();
        log.info("赠送积分响应:{}", scoreResult);
        return "success" + scoreResult;
    }

    @HystrixCommand(fallbackMethod = "addScoreFallback")
    public String addScoreH() {
        return restTemplate.getForObject("http://localhost:8081/score/addScore", String.class);
    }

    public String addScoreFallback() {
        return "保留记录，日后发放积分";
    }
}
