package com.engine.flashsale.vo;

import com.engine.flashsale.entity.Goods;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品详情展示对象
 * 聚合了 Goods (基础信息) 和 SeckillGoods (秒杀信息)
 * @author ethan
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsVo extends Goods {

    /**
     * 秒杀价
     */
    private BigDecimal seckillPrice;

    /**
     * 秒杀库存
     * 注意：父类 Goods 也有 goodsStock，那是总库存。
     * 这里是专门分配给秒杀活动的库存。
     */
    private Integer stockCount;

    /**
     * 秒杀开始时间
     */
    private Date startDate;

    /**
     * 秒杀结束时间
     */
    private Date endDate;
}
