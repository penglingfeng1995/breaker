package com.plf.breaker.hystrixcl.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

import static com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager.CORE_SIZE;
import static com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS;
import static com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager.MAX_QUEUE_SIZE;

@Service
@DefaultProperties(
        threadPoolKey = "ScoreServiceaaa", threadPoolProperties = {
        @HystrixProperty(name = CORE_SIZE, value = "1"),
        @HystrixProperty(name = MAX_QUEUE_SIZE,value = "10")
}, commandProperties = {
        @HystrixProperty(name = EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "6000")
})
public class ScoreService {

    @Autowired
    private RestTemplate restTemplate;

    public String addScore() {
        return restTemplate.getForObject("http://localhost:8081/score/addScore", String.class);
    }

    @HystrixCommand(fallbackMethod = "addScoreFallback", commandKey = "ccc")
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

    @HystrixCommand
    public String decrScore() {
        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "扣减积分成功";
    }
}