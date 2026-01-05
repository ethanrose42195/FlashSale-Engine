package com.engine.flashsale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.engine.flashsale.entity.User;
import com.engine.flashsale.vo.LoginVo;
import com.engine.flashsale.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户服务接口
 * <p>
 * 提供用户登录、凭证校验、用户信息获取等功能
 * Controller 只需调用此 Service，无需关注实现细节
 *
 * @author ethan
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录
     *
     * @param loginVo  前端传入的登录信息 (手机号 + 明文密码)
     * @param request  HttpServletRequest，用于操作 Cookie
     * @param response HttpServletResponse，用于写入登录凭证
     * @return RespBean 统一响应对象，包含登录结果和 ticket
     */
    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据用户凭证 Ticket 获取登录用户信息
     *
     * @param ticket   登录凭证
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return User 已登录用户对象，若凭证无效返回 null
     */
    User getUserByTicket(String ticket, HttpServletRequest request, HttpServletResponse response);
}
