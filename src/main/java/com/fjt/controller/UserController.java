package com.fjt.controller;

import com.fjt.annotation.RequireAdmin;
import com.fjt.config.JwtProperties;
import com.fjt.pojo.dto.LoginDTO;
import com.fjt.pojo.dto.UserQueryDTO;
import com.fjt.pojo.entity.User;
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
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO loginDTO) {
        UserVO userVO = userService.login(loginDTO);
        if (userVO != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", userVO.getId());
            claims.put("username", userVO.getUsername());
            String token = JwtUtils.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);
            return Result.success(token);
        }
        return Result.error("用户名或密码错误");
    }

    @GetMapping
    public Result<List<UserVO>> list(UserQueryDTO query) {
        return Result.success(userService.search(query));
    }

    @GetMapping("/{id}")
    public Result<UserVO> getById(@PathVariable Long id) {
        return Result.success(userService.findById(id));
    }

    @RequireAdmin
    @PostMapping
    public Result<Void> add(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }

    @RequireAdmin
    @PutMapping("/{id}")
    public Result<Void> update(@RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        userService.update(user);
        return Result.success();
    }

    @RequireAdmin
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return Result.success();
    }
}