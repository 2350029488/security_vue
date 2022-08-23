package com.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.security.entity.Menu;
import com.security.mapper.MenuMapper;
import com.security.service.IMenuService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
   @Autowired
   private MenuMapper menuMapper;
    @Override
    public List<String> selectAllParamsByUserId(Integer id) {
        return menuMapper.selectAllParamsByUserId(id);
    }
}
