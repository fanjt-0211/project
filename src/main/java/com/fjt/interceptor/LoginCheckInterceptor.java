package com.fjt.interceptor;

import com.fjt.config.JwtProperties;
import com.fjt.pojo.Result;
import com.fjt.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(jwtProperties.getAdminTokenName());
        
        if (token == null || token.isEmpty()) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(new ObjectMapper().writeValueAsString(Result.error("请先登录")));
            return false;
        }
        
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        
        try {
            jwtUtils.parseToken(token);
            return true;
        } catch (Exception e) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(new ObjectMapper().writeValueAsString(Result.error("token无效")));
            return false;
        }
    }
}