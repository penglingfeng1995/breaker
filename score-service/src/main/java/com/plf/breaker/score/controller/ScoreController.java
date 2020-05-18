package com.plf.breaker.score.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author penglf3
 */
@RestController
public class ScoreController {

    int i = 0;

    @GetMapping("addScore")
    public String addScore() {
        i++;
        if (i % 3 == 0) {
            sleep(5L);
        }
        return "积分增加成功";
    }

    private void sleep(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
