package com.fjt.interceptor;

import com.fjt.annotation.RequireAdmin;
import com.fjt.config.JwtProperties;
import com.fjt.pojo.Result;
import com.fjt.utils.JwtUtils;
import com.fjt.utils.UserHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

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
        
        try {
            Claims claims = JwtUtils.parseJWT(jwtProperties.getAdminSecretKey(), token);

            Long userId = claims.get("id", Long.class);
            Integer role = claims.get("role", Integer.class);
            UserHolder.setUserId(userId);
            UserHolder.setRole(role);

            if (handler instanceof HandlerMethod hm) {
                RequireAdmin requireAdmin = hm.getMethodAnnotation(RequireAdmin.class);
                if (requireAdmin != null) {
                    if (role == null || role != 1) {
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        PrintWriter writer = response.getWriter();
                        writer.write(new ObjectMapper().writeValueAsString(Result.error("无权限操作")));
                        return false;
                    }
                }
            }
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