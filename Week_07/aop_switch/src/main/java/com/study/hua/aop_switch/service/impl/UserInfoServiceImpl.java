package com.study.hua.aop_switch.service.impl;

import com.study.hua.aop_switch.Mapper.UserInfoMapper;
import com.study.hua.aop_switch.annotation.ReadOnly;
import com.study.hua.aop_switch.pojo.UserInfo;
import com.study.hua.aop_switch.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public int insert(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo);
    }

    @ReadOnly
    @Override
    public UserInfo getById(Integer userId) {
        return userInfoMapper.getById(userId);
    }
}
