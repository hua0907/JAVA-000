package com.study.hua.aop_switch.Mapper;

import com.study.hua.aop_switch.pojo.UserInfo;

public interface UserInfoMapper {

    int insert(UserInfo userInfo);

    UserInfo getById(Integer userId);
}
