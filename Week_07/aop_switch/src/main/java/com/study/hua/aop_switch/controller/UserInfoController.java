package com.study.hua.aop_switch.controller;


import com.study.hua.aop_switch.pojo.IdDto;
import com.study.hua.aop_switch.pojo.UserInfo;
import com.study.hua.aop_switch.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/insert")
    public Integer insert(@RequestBody UserInfo userInfo) {
        return userInfoService.insert(userInfo);
    }

    @PostMapping("/getById")
    public UserInfo getById(@RequestBody IdDto idDto) {
        return userInfoService.getById(idDto.getId());
    }
}
