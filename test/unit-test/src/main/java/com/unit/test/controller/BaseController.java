package com.unit.test.controller;

import com.auth0.jwt.JWT;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController {
    /**
     * 获取用户账号
     *
     * @return 用户名
     */
    protected String getAccount() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        if (StringUtils.isNotBlank(token)) {
            return JWT.decode(token).getAudience().get(0);
        }
        return null;
    }
}
