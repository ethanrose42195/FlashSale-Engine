package com.engine.flashsale.utils;

import java.util.UUID;

/**
 * UUID 工具类
 * @author ethan
 */
public class UUIDUtil {
    /**
     * 生成 UUID
     * @return String
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
