package com.engine.flashsale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.engine.flashsale.entity.Goods;
import com.engine.flashsale.mapper.GoodsMapper;
import com.engine.flashsale.service.GoodsService;
import com.engine.flashsale.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品服务实现类
 * @author ethan
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsVo> findGoodsVo() {
        // TODO [Phase 2]: 这里是高并发读的“重灾区”。
        // 优化方案：先查 Redis 缓存 -> 如果没有 -> 查数据库 -> 写入 Redis。
        return goodsMapper.findGoodsVo();
    }

    @Override
    public GoodsVo findGoodsVoByGoodsId(Long goodsId) {
        // TODO [Phase 2]: 商品详情页流量巨大。
        // 优化方案：这里需要做“页面静态化”或者 URL 缓存。
        // 同时要注意“缓存击穿”问题（针对热点 Key）。
        return goodsMapper.findGoodsVoByGoodsId(goodsId);
    }
}
