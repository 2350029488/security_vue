package com.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.security.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IMenuService extends IService<Menu> {
    List<String> selectAllParamsByUserId(Integer id);
}
