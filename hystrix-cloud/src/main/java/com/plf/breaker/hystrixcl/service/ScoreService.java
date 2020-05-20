package com.plf.breaker.hystrixcl.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager.*;

@Service
@DefaultProperties(
        threadPoolKey = "ScoreServiceaaa",threadPoolProperties = {
        @HystrixProperty(name = CORE_SIZE,value = "15"),
        @HystrixProperty(name = MAX_QUEUE_SIZE,value = "10")
})
public class ScoreService {

    @Autowired
    private RestTemplate restTemplate;

    public String addScore() {
        return restTemplate.getForObject("http://localhost:8081/score/addScore", String.class);
    }

    @HystrixCommand(fallbackMethod = "addScoreFallback",commandKey = "ccc")
//    @HystrixCommand
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS,value = "500")
//    })
    public String addScoreH() {
        return restTemplate.getForObject("http://localhost:8081/score/addScore", String.class);
    }

    public String addScoreFallback() {
        return "保留记录，日后发放积分";
    }
}