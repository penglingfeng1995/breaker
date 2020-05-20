package com.plf.breaker.hystrixcl.controller;

import com.plf.breaker.hystrixcl.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("registerH2")
    public String registerH2(){
        return registerService.registerH2();
    }
}