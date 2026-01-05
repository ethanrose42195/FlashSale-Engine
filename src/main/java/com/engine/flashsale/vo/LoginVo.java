package com.engine.flashsale.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 登录请求对象
 * @author ethan
 */
@Data
public class LoginVo {
    @NotNull
    private String mobile;

    @NotNull
    private String password;
}
