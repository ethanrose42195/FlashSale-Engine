package com.engine.flashsale.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Web MVC 配置类
 *
 * 作用：
 * 1. 注册自定义参数解析器 UserArgumentResolver
 * 2. 让 Controller 方法中参数类型为 User 时自动注入登录用户
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final UserArgumentResolver userArgumentResolver;

    @Autowired
    public WebConfig(UserArgumentResolver userArgumentResolver) {
        this.userArgumentResolver = userArgumentResolver;
    }

    /**
     * 注册自定义参数解析器
     * Spring MVC 会调用列表中的解析器来解析 Controller 方法参数
     *
     * @param resolvers 参数解析器列表
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolver);
    }
}
