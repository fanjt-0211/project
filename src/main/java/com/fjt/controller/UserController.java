package com.fjt.controller;

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

    /**
     * 用户登录
     */
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

    /**
     * 查询所有用户
     */
    @GetMapping
    public Result<List<UserVO>> list() {
        return Result.success(userService.findAll());
    }

    /**
     * 根据id查询用户
     */
    @GetMapping("/{id}")
    public Result<UserVO> getById(@PathVariable Long id) {
        return Result.success(userService.findById(id));
    }

    /**
     * 通用查询接口 - 支持多条件模糊查询
     * 参数可为空，为空则查询所有
     */
    @GetMapping("/search")
    public Result<List<UserVO>> search(UserQueryDTO query) {
        return Result.success(userService.search(query));
    }

    /**
     * 添加用户
     */
    @PostMapping
    public Result<Void> add(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }

    /**
     * 修改用户
     */
    @PutMapping("/{id}")
    public Result<Void> update(@RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        userService.update(user);
        return Result.success();
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return Result.success();
    }
}
