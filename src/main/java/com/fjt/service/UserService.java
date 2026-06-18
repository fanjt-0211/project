package com.fjt.service;

import com.fjt.pojo.dto.LoginDTO;
import com.fjt.pojo.entity.User;
import com.fjt.pojo.vo.UserVO;

import java.util.List;

public interface UserService {
    UserVO login(LoginDTO loginDTO);
    void add(User user);
    void update(User user);
    void delete(Long id);
    UserVO findById(Long id);
    User findByUsername(String username);
    List<UserVO> findAll();
}