package com.engine.flashsale;

import com.engine.flashsale.mapper.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlashSaleEngineApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testDbConn(){
        System.out.println("user--------------------------------------");
        System.out.println(userMapper.selectList(null));
        System.out.println("goods--------------------------------------");
        System.out.println(goodsMapper.selectList(null));
        System.out.println("seckillGoods--------------------------------------");
        System.out.println(seckillGoodsMapper.selectList(null));
        System.out.println("order--------------------------------------");
        System.out.println(orderMapper.selectList(null));
        System.out.println("seckillOrder--------------------------------------");
        System.out.println(seckillOrderMapper.selectList(null));
    }


}
