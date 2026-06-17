package com.fjt.controller;

import com.fjt.pojo.Result;
import com.fjt.pojo.User;
import com.fjt.service.UserService;
import com.fjt.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody User user) {
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            String token = JwtUtils.generateToken(loginUser.getId(), loginUser.getUsername());
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user", loginUser);
            return Result.success(data);
        }
        return Result.error("用户名或密码错误");
    }
}