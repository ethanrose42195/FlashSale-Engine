package com.engine.flashsale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.engine.flashsale.entity.User;
import com.engine.flashsale.enums.RespBeanEnum;
import com.engine.flashsale.mapper.UserMapper;
import com.engine.flashsale.service.UserService;
import com.engine.flashsale.utils.UUIDUtil;
import com.engine.flashsale.vo.LoginVo;
import com.engine.flashsale.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 用户服务实现类
 * <p>
 * 功能：
 * 1. 用户登录（校验手机号 + 密码）
 * 2. 生成登录凭证 ticket 并写入 Redis + Cookie
 * 3. 根据 ticket 获取登录用户
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static final long SESSION_EXPIRE_MINUTES = 30;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 用户登录
     */
    @Override
    public RespBean doLogin(LoginVo loginVo,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String inputPassword = loginVo.getPassword();

        // 1. 根据手机号查询用户
        User user = userMapper.selectById(Long.valueOf(mobile));
        if (user == null) {
            log.warn("登录失败：用户不存在，mobile={}", mobile);
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        // 2. 校验密码
        if (!passwordEncoder.matches(inputPassword, user.getPassword())) {
            log.warn("登录失败：密码错误，mobile={}", mobile);
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        // 3. 创建会话：生成 Ticket + 存 Redis + 写 Cookie
        String ticket = createSession(user, response);

        return RespBean.success(ticket);
    }

    /**
     * 根据 Ticket 获取用户信息
     */
    @Override
    public User getUserByTicket(String ticket,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        if (!StringUtils.hasText(ticket)) {
            return null;
        }

        User user = (User) redisTemplate.opsForValue().get("user:" + ticket);

        if (user != null) {
            // 滑动过期：每次访问延长 TTL
            redisTemplate.expire("user:" + ticket, SESSION_EXPIRE_MINUTES, TimeUnit.MINUTES);
        }

        return user;
    }

    /**
     * 创建用户会话
     * 1. 生成唯一 ticket
     * 2. 存入 Redis 并设置过期时间
     * 3. 写入 HttpOnly Cookie
     */
    private String createSession(User user, HttpServletResponse response) {
        String ticket = UUIDUtil.uuid();

        // Redis 存储
        redisTemplate.opsForValue().set("user:" + ticket, user, SESSION_EXPIRE_MINUTES, TimeUnit.MINUTES);

        // Cookie 写入
        Cookie cookie = new Cookie("userTicket", ticket);
        cookie.setPath("/");
        cookie.setHttpOnly(true); // 避免 JS 获取
        cookie.setMaxAge((int) TimeUnit.MINUTES.toSeconds(SESSION_EXPIRE_MINUTES));
        response.addCookie(cookie);

        log.info("用户登录成功：userId={} ticket={}", user.getId(), ticket);
        return ticket;
    }
}
