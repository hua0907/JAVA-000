package com.hua.study.bean_demo.config;

import com.hua.study.bean_demo.pojo.Student2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Student2 s2() {
        return new Student2();
    }
}
