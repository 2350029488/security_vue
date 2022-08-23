package com.security.mapper;

import com.security.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 黄隆
 * @since 2022-08-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
