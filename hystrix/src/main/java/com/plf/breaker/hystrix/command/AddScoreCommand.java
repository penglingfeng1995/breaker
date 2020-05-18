package com.plf.breaker.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.plf.breaker.hystrix.service.RegisterService;

/**
 * @author penglf3
 */
public class AddScoreCommand extends HystrixCommand<String> {

    private RegisterService registerService;

    public AddScoreCommand(RegisterService registerService) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RegisterService")));
        this.registerService = registerService;
    }

    @Override
    protected String run() throws Exception {
        return registerService.addScore();
    }

    @Override
    protected String getFallback() {
        return "保存记录,日后发放积分";
    }
}
