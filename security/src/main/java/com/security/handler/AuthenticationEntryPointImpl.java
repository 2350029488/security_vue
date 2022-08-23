package com.security.handler;

import com.alibaba.fastjson.JSON;
import com.security.utils.Message;
import com.security.utils.WebUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
            Message message = Message.fail().add("message", "统一管理--认证失败");
            String messages = JSON.toJSONString(message);
            WebUtil.renderString(httpServletResponse,messages);
        }
    }

