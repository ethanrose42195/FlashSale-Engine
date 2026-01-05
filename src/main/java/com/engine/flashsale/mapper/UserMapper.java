package com.engine.flashsale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.engine.flashsale.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper
 * @author ethan
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
