package com.engine.flashsale.controller;

import com.engine.flashsale.service.UserService;
import com.engine.flashsale.vo.LoginVo;
import com.engine.flashsale.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录接口 Controller
 * <p>
 * 负责处理用户登录请求，将登录凭证（ticket）写入 Cookie
 * 并返回统一响应对象 RespBean
 * </p>
 * <p>
 * 设计原则：
 * 1. Controller 只负责接收请求和返回响应
 * 2. 登录业务逻辑交给 UserService 处理
 * 3. 请求参数校验使用 @Valid 注解
 * </p>
 *
 * @author ethan
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录接口
     *
     * @param loginVo  前端提交的登录数据 (手机号 + 密码等)
     * @param request  HttpServletRequest，用于在 UserService 中处理 Cookie/Session
     * @param response HttpServletResponse，用于写入登录凭证
     * @return RespBean 统一响应对象，包含登录成功/失败信息
     */
    @PostMapping("/doLogin")
    public RespBean doLogin(@Valid @RequestBody LoginVo loginVo,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        return userService.doLogin(loginVo, request, response);
    }
}
