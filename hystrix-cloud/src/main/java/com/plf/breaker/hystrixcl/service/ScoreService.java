package com.plf.breaker.hystrixcl.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

import static com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE;
import static com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD;
import static com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS;
import static com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager.METRICS_ROLLING_PERCENTILE_TIME_IN_MILLISECONDS;

@Service
//@DefaultProperties(
//        threadPoolKey = "ScoreServiceaaa", threadPoolProperties = {
//        @HystrixProperty(name = CORE_SIZE, value = "1"),
//        @HystrixProperty(name = MAX_QUEUE_SIZE, value = "10")
//}, commandProperties = {
//        @HystrixProperty(name = EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "6000")
//})
public class ScoreService {

    @Autowired
    private RestTemplate restTemplate;

    public String addScore() {
        return restTemplate.getForObject("http://localhost:8081/score/addScore", String.class);
    }

    //        @HystrixCommand(fallbackMethod = "addScoreFallback")
//    @HystrixCommand
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS,value = "500")
//    })
    @HystrixCommand(fallbackMethod = "addScoreFallback",
            commandProperties = {
                    @HystrixProperty(name = METRICS_ROLLING_PERCENTILE_TIME_IN_MILLISECONDS, value = "5000"),
                    @HystrixProperty(name = CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "3"),
                    @HystrixProperty(name = CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "50"),
                    @HystrixProperty(name = CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "3000")
            }
    )
    public String addScoreH() {
        return restTemplate.getForObject("http://localhost:8081/score/addScore", String.class);
    }

    public String addScoreFallback() {
        return "保留记录，日后发放积分";
    }

    @HystrixCommand(threadPoolKey = "decrScore")
    public String decrScore() {
        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "扣减积分成功";
    }
}