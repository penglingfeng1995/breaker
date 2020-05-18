package com.plf.breaker.score.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author penglf3
 */
@RestController
public class ScoreController {

    @GetMapping("addScore")
    public String addScore(){
        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "积分增加成功";
    }
}
