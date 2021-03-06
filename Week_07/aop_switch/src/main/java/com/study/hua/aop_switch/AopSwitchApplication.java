package com.study.hua.aop_switch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@EnableAspectJAutoProxy
@MapperScan("com.study.hua.aop_switch.Mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AopSwitchApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopSwitchApplication.class, args);
    }

}
