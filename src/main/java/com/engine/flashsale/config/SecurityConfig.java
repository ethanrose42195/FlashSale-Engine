package com.engine.flashsale.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security 配置类
 * <p>
 * 作用：
 * 1. 禁用 Spring Security 默认的 CSRF 校验，适合前后端分离 + Token 模式
 * 2. 放行所有接口访问，接口认证由自定义 Interceptor/AOP 控制
 * 3. 提供全局 PasswordEncoder Bean (BCrypt) 用于密码加密和校验
 * @author ethan
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * HTTP 安全策略配置
     *
     * 当前策略：
     * - CSRF 禁用
     * - 所有接口放行
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll();
    }

    /**
     * 密码加密 Bean
     * 使用 BCrypt 算法自动加盐，提高密码安全性
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
