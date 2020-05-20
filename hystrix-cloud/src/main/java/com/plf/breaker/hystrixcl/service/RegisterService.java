package com.plf.breaker.hystrixcl.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author penglf3
 */
@Slf4j
@Service
public class RegisterService {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private LotteryService lotteryService;

    public String register() {
        log.info("注册成功");
        String scoreResult = scoreService.addScore();
        log.info("赠送积分响应:{}", scoreResult);
        return "success" + scoreResult;
    }


    public String registerH() {
        log.info("注册成功");
        String scoreResult = scoreService.addScoreH();
        log.info("赠送积分响应:{}", scoreResult);
        return "success" + scoreResult;
    }

    public String registerH2() {
        log.info("注册成功");
        String scoreResult = scoreService.addScoreH();
        log.info("赠送积分响应:{}", scoreResult);
        String drawResult = lotteryService.addDrawTimeH();
        return "success" + scoreResult + ":" + drawResult;
    }
}