package com.engine.flashsale.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author ethan
 */

@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {

    // 通用状态
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务端异常"),

    // 登录模块 5002xx
    LOGIN_ERROR(500210, "用户名或密码不正确"),
    MOBILE_ERROR(500211, "手机号码格式不正确"),

    // 秒杀模块 5005xx
    EMPTY_STOCK(500500, "库存不足"),
    REPEATE_ERROR(500501, "该商品每人限购一件"),
    SESSION_ERROR(500210, "Session不存在或者已经失效");

    private final Integer code;
    private final String message;
}
