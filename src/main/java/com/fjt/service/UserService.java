package com.fjt.service;

import com.fjt.pojo.dto.LoginDTO;
import com.fjt.pojo.dto.UserDTO;
import com.fjt.pojo.dto.UserQueryDTO;
import com.fjt.pojo.vo.UserVO;

import java.util.List;

public interface UserService {
    UserVO login(LoginDTO loginDTO);
    void add(UserDTO userDTO);
    void update(UserDTO userDTO);
    void updateStatus(Long id, Integer status);
    UserVO findById(Long id);
    List<UserVO> search(UserQueryDTO query);
}
