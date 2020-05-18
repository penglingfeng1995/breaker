package com.plf.breaker.hystrix.controller;

import com.plf.breaker.hystrix.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author penglf3
 */
@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("register")
    public String register(){
        return registerService.register();
    }

    @PostMapping("registerH")
    public String registerH(){
        return registerService.registerH();
    }
}
