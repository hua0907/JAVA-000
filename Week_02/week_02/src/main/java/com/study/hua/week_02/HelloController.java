package com.study.hua.week_02;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
