package com.security.service.impl;

import com.security.entity.User;
import com.security.mapper.UserMapper;
import com.security.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.security.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 黄隆
 * @since 2022-08-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService{

}
