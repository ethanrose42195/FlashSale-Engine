package com.engine.flashsale.config;

import com.engine.flashsale.entity.User;
import com.engine.flashsale.service.UserService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * UserArgumentResolver
 * <p>
 * 作用：
 * 1. 当 Controller 方法参数是 User 类型时自动注入
 * 2. 优先从 Cookie 获取 ticket，兼容 Header（App端）
 * 3. 调用 UserService 从 Redis 获取用户信息
 * 4. 未登录返回 null
 * @author ethan
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 只处理 User 类型参数
        return parameter.getParameterType() == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);

        // 1. 获取登录凭证 ticket
        String ticket = getTicketFromRequest(request);

        if (!StringUtils.hasText(ticket)) {
            return null; // 未登录
        }

        // 2. 从 Redis 获取用户
        return userService.getUserByTicket(ticket, request, response);
    }

    private String getTicketFromRequest(HttpServletRequest request) {
        // 优先 Cookie
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("userTicket".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        // 再尝试 Header
        return request.getHeader("userTicket");
    }
}
