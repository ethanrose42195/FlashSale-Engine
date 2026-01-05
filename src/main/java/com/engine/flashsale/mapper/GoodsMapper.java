package com.engine.flashsale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.engine.flashsale.entity.Goods;
import com.engine.flashsale.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品Mapper
 * @author ethan
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 获取商品列表（包含秒杀信息）
     * @return List<GoodsVo>
     */
    List<GoodsVo> findGoodsVo();

    /**
     * 根据ID获取商品详情（包含秒杀信息）
     * @param goodsId 商品ID
     * @return GoodsVo
     */
    GoodsVo findGoodsVoByGoodsId(@Param("goodsId") Long goodsId);
}
