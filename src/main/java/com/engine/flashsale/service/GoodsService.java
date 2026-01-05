package com.engine.flashsale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.engine.flashsale.entity.Goods;
import com.engine.flashsale.vo.GoodsVo;

import java.util.List;

/**
 * 商品服务接口
 * @author ethan
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 获取商品列表（包含秒杀信息）
     * @return List<GoodsVo>
     */
    List<GoodsVo> findGoodsVo();

    /**
     * 获取商品详情（包含秒杀信息）
     * @param goodsId 商品ID
     * @return GoodsVo
     */
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
