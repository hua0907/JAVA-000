package com.study.hua.aop_switch.Mapper;

import com.study.hua.aop_switch.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper {

    int insert(UserInfo userInfo);

    UserInfo getById(@Param("userId") Integer userId);
}
