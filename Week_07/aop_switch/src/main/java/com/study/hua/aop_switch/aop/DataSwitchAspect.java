package com.study.hua.aop_switch.aop;

import com.study.hua.aop_switch.DataSourceEnum.DataSourceEnum;
import com.study.hua.aop_switch.annotation.ReadOnly;
import com.study.hua.aop_switch.holder.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Random;

@Component
public class DataSwitchAspect {

    @Pointcut("@annotation(com.study.hua.aop_switch.annotation.ReadOnly)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(ProceedingJoinPoint pjp) {
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        ReadOnly annotation = method.getAnnotation(ReadOnly.class);
        if (annotation != null) {
            //从库负载均衡
            Random random = new Random(47);
            //随机下标 1到3
            int index = random.nextInt(2) + 1;
            DataSourceEnum dataSourceEnum = DataSourceEnum.values()[index];
            String currentDataSource = dataSourceEnum.getName();
            DataSourceContextHolder.set(currentDataSource);
        }

    }

    @After("pointCut()")
    public void after() {
        DataSourceContextHolder.clear();
    }
}



