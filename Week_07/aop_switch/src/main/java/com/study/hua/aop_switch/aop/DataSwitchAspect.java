package com.study.hua.aop_switch.aop;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class DataSwitchAspect {

    @Pointcut("@annotation(com.study.hua.aop_switch.annotation.ReadOnly)")
    public void pointCut() {

    }



}



