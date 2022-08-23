package com.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.security.entity.User;
import com.security.mapper.MenuMapper;
import com.security.mapper.UserMapper;
import com.security.utils.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName,username);
        User getUser = userMapper.selectOne(lambdaQueryWrapper);
        System.out.println(getUser);
        if (StringUtils.isNotBlank(username)){
            List<String> list = menuMapper.selectAllParamsByUserId(getUser.getId());
            UserDetails loginUser = new LoginUser(getUser,list);
            return loginUser;
        }else {
            throw  new RuntimeException("用户名或者密码错误！！！！");
        }
    }
}
