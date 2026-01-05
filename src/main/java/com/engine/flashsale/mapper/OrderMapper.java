package com.engine.flashsale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.engine.flashsale.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单表Mapper
 * @author ethan
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
