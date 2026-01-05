package com.engine.flashsale;

import com.engine.flashsale.mapper.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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


    @Test
    void testRedisConn(){
        String testKey = "redisTestKey";
        String testValue = "HelloRedis";

        // 1. 写入 Redis
        redisTemplate.opsForValue().set(testKey, testValue);

        // 2. 读取 Redis
        Object value = redisTemplate.opsForValue().get(testKey);

        System.out.println("Redis 返回值: " + value);

        // 3. 可选：删除测试 key
        redisTemplate.delete(testKey);
    }

    @Test
    void test(){
        System.out.println("=====================");
        System.out.println(redisTemplate.opsForValue().get("user:1a765367cbd44c7dac8530eb6aa0e30c"));
        System.out.println("=====================");
    }
}
