package com.fjt.controller;

import com.fjt.pojo.dto.LoginDTO;
import com.fjt.pojo.Result;
import com.fjt.pojo.vo.UserVO;
import com.fjt.service.UserService;
import com.fjt.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody LoginDTO loginDTO) {
        UserVO userVO = userService.login(loginDTO);
        if (userVO != null) {
            String token = JwtUtils.generateToken(userVO.getId(), userVO.getUsername());
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user", userVO);
            return Result.success(data);
        }
        return Result.error("用户名或密码错误");
    }

    @GetMapping("/users")
    public Result<List<UserVO>> list() {
        return Result.success(userService.findAll());
    }

    @GetMapping("/users/{id}")
    public Result<UserVO> getById(@PathVariable Long id) {
        return Result.success(userService.findById(id));
    }
}