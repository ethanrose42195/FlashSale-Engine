package com.engine.flashsale.controller;

import com.engine.flashsale.entity.User;
import com.engine.flashsale.service.GoodsService;
import com.engine.flashsale.vo.GoodsVo;
import com.engine.flashsale.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品控制器
 * * @author ethan
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 获取商品列表
     * @return 标准JSON结果
     */
    @GetMapping("/list")
    public RespBean list(User user) {
        if (user == null) {
            return RespBean.error(com.engine.flashsale.enums.RespBeanEnum.SESSION_ERROR);
        }

        List<GoodsVo> goodsVoList = goodsService.findGoodsVo();

        return RespBean.success(goodsVoList);
    }

    /**
     * 获取商品详情
     * @param goodsId 商品ID
     * @return 标准JSON结果
     */
    @GetMapping("/detail/{goodsId}")
    public RespBean toDetail(User user, @PathVariable Long goodsId) {
        if (user == null) {
            return RespBean.error(com.engine.flashsale.enums.RespBeanEnum.SESSION_ERROR);
        }

        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        return RespBean.success(goodsVo);
    }
}
