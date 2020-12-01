package com.study.hua.aop_switch.service;

import com.study.hua.aop_switch.pojo.UserInfo;

public interface UserInfoService {
    int insert(UserInfo userInfo);

    UserInfo getById(Integer userId);
}
