package com.fjt.controller;

import com.fjt.annotation.RequireAdmin;
import com.fjt.config.JwtProperties;
import com.fjt.pojo.dto.LoginDTO;
import com.fjt.pojo.dto.UserDTO;
import com.fjt.pojo.dto.UserQueryDTO;
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
            claims.put("role", userVO.getRole());
            String token = JwtUtils.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);
            Result<String> result = Result.success(token);
            result.setMessage("登录成功");
            return result;
        }
        return Result.error("用户名或密码错误");
    }

    @GetMapping
    public Result<List<UserVO>> list(UserQueryDTO query) {
        return Result.success(userService.search(query));
    }

    @GetMapping("/{id}")
    public Result<UserVO> getById(@PathVariable Long id) {
        UserVO user = userService.findById(id);
        if (user == null) {
            return Result.error("用户不存在");
        }
        return Result.success(user);
    }

    @RequireAdmin
    @PostMapping
    public Result<Void> add(@RequestBody UserDTO userDTO) {
        userService.add(userDTO);
        return Result.success();
    }

    /**
     * 修改用户信息
     *
     * @param userDTO
     * @return
     */
    @RequireAdmin
    @PutMapping
    public Result<Void> update(@RequestBody UserDTO userDTO) {
        userService.update(userDTO);
        return Result.success();
    }

    /**
     * 启用/禁用用户
     *
     * @param status
     * @param userDTO
     * @return
     */
    @RequireAdmin
    @PostMapping("/status/{status}")
    public Result<Void> updateStatus(@PathVariable Integer status, @RequestBody UserDTO userDTO) {
        userService.updateStatus(userDTO.getId(), status);
        return Result.success();
    }
}